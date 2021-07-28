package com.rajiv.BankApp.service;

import com.rajiv.BankApp.dto.*;
import com.rajiv.BankApp.dto.shared.AccountCurrency;
import com.rajiv.BankApp.dto.shared.AccountMessage;
import com.rajiv.BankApp.dto.shared.AccountType;
import com.rajiv.BankApp.exceptions.AccountServiceException;
import com.rajiv.BankApp.model.*;
import com.rajiv.BankApp.repository.*;
import com.rajiv.BankApp.request.WithdrawModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final double OVERDRAFT = -10000.00;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AtmAllocationRepository atmAllocationRepository;

    @Autowired
    private CreditCardLimitRepository creditCardLimitRepository;

    @Autowired
    private DenominationRepository denominationRepository;

    @Override
    public List<AccountDto> getAccountByClientId(int id) {

        List<AccountDto> returnValue = new ArrayList<>();
        List<AccountEntity> accounts = accountRepository.findByClientId(id);

        if (accounts == null || accounts.isEmpty())
            throw new AccountServiceException(AccountMessage.ACCOUNT_NOT_FOUND.message);

        accounts.sort(Comparator.comparing(AccountEntity::getDisplayBalance).reversed());

        for (AccountEntity accountEntity : accounts) {
            AccountDto accountDto = new AccountDto();
            BeanUtils.copyProperties(accountEntity, accountDto);

            AccountTypeEntity accountType = accountTypeRepository.findByAccountTypeCode(accountDto.getAccountTypeCode());
            accountDto.setAccountType(accountType.getDescription());
            returnValue.add(accountDto);
        }

        return returnValue;
    }

    @Override
    public CurrencyDto getCurrencyByCode(String code) {

        CurrencyEntity currency = currencyRepository.findByCurrencyCode(code);

        if (currency == null)
            throw new AccountServiceException(AccountMessage.CURRENCY_NOT_FOUND.message);

        CurrencyDto returnValue = new CurrencyDto();
        BeanUtils.copyProperties(currency, returnValue);

        return returnValue;
    }

    @Override
    public CurrencyConversionDto getCurrConversionByCode(String code) {

        CurrencyConversionEntity conversion = currencyConversionRepository.findByCurrencyCode(code);

        if (conversion == null)
            throw new AccountServiceException(AccountMessage.CURRENCY_CONVERSION_NOT_FOUND.message);

        CurrencyConversionDto returnValue = new CurrencyConversionDto();
        BeanUtils.copyProperties(conversion, returnValue);

        return returnValue;
    }

    @Override
    public List<CurrencyAccountDto> getCurrencyAccountByClientId(int id) {

        List<CurrencyAccountDto> returnValue = new ArrayList<>();
        List<AccountDto> accounts = getAccountByClientId(id);

        for (AccountDto account : accounts) {
            if (!account.getCurrencyCode().equals(AccountCurrency.SOUTH_AFRICAN_RAND.currency)) {
                CurrencyAccountDto currencyAccount = new CurrencyAccountDto();
                CurrencyConversionDto conversion = getCurrConversionByCode(account.getCurrencyCode());

                currencyAccount.setAccountNumber(account.getClientAccountNumber());
                currencyAccount.setCurrencyCode(account.getCurrencyCode());
                currencyAccount.setAccountBalance(account.getDisplayBalance());
                currencyAccount.setConversionRate(conversion.getRate());
                currencyAccount.setConvertedAmount(account.getDisplayBalance(), conversion.getRate());

                returnValue.add(currencyAccount);
            }
        }

        if (returnValue.isEmpty())
            throw new AccountServiceException(AccountMessage.ACCOUNT_NOT_FOUND.message);

        returnValue.sort(Comparator.comparing(CurrencyAccountDto::getConvertedAmount).reversed());

        return returnValue;
    }

    @Override
    public AccountDto getAccountWithdrawal(int atmId, int clientId, String accountNumber) {

        //check if account exists
        AccountEntity account = accountRepository.findByClientAccountNumberAndClientId(accountNumber, clientId);

        if (account == null)
            throw new AccountServiceException(AccountMessage.ACCOUNT_NOT_FOUND.message);

        AccountDto returnValue = new AccountDto();
        BeanUtils.copyProperties(account, returnValue);
        returnValue.setAtmId(atmId);

        AccountTypeEntity accountType = accountTypeRepository.findByAccountTypeCode(returnValue.getAccountTypeCode());
        returnValue.setAccountType(accountType.getDescription());

        return returnValue;
    }

    @Override
    public AtmDto getAtmById(int atmId) {

        //check if atm registered and has funds allocated
        AtmEntity atm = atmRepository.findByAtmId(atmId);

        if (atm == null)
            throw new AccountServiceException(AccountMessage.ATM_NOT_REGISTERED_OR_FUNDED.message);

        List<AtmAllocationEntity> allocations = atm.getAllocations();

        if (allocations.isEmpty())
            throw new AccountServiceException(AccountMessage.ATM_NOT_REGISTERED_OR_FUNDED.message);

        AtmDto returnValue = new AtmDto();
        BeanUtils.copyProperties(atm, returnValue);

        return returnValue;
    }

    @Override
    public List<DispenseDto> getWithdrawalByAmount(int atmId, String accountNumber, double amount) {

        //check client balance
        AccountEntity clientAccount = accountRepository.findByClientAccountNumber(accountNumber);

        if (clientAccount.getAccountTypeCode().equals(AccountType.CHEQUE.accType)) {
            if ((clientAccount.getDisplayBalance() - amount) < OVERDRAFT)
                throw new AccountServiceException(AccountMessage.INSUFFICIENT_FUNDS.message);

        } else if (clientAccount.getAccountTypeCode().equals(AccountType.SAVINGS.accType)) {
            if ((clientAccount.getDisplayBalance() < 0) || ((clientAccount.getDisplayBalance() - amount) < 0))
                throw new AccountServiceException(AccountMessage.INSUFFICIENT_FUNDS.message);

        } else if (clientAccount.getAccountTypeCode().equals(AccountType.CREDIT_CARD.accType)) {
            CreditCardLimitEntity creditCard = creditCardLimitRepository.findByClientAccountNumber(accountNumber);

            if ((clientAccount.getDisplayBalance() + amount) > creditCard.getAccountLimit())
                throw new AccountServiceException(AccountMessage.INSUFFICIENT_FUNDS.message);
        }

        return getAtmDispense(atmId, amount);
    }

    @Override
    public List<DispenseDto> getAtmDispense(int atmId, double amount) {

        List<DispenseDto> returnValue = new ArrayList<>();

        //get atm allocations for atmId
        AtmEntity atm = atmRepository.findByAtmId(atmId);
        List<AtmAllocationEntity> allocations = atm.getAllocations();

        //sort denominations list highest to lowest
        allocations.sort(Comparator.comparing(AtmAllocationEntity::getDenominationId).reversed());

        double remainder = amount;

        //loop through list of allocation
        for (AtmAllocationEntity allocation : allocations) {
            DispenseDto notes = new DispenseDto();

            //get VALUE from denominations for denominationId - NOTES only
            DenominationEntity denomination = denominationRepository.findByDenominationIdAndDenominationTypeCode(allocation.getDenominationId(), 'N');
            double value = denomination.getValue();

            //do math keeping in mind the denomination COUNT in allocations
            if (remainder > 0) {
                int denominationCount = (int) (remainder / value);

                if (denominationCount > allocation.getCount())
                    denominationCount = allocation.getCount();

                //add to notes to dispense and calc new remainder
                if (denominationCount > 0) {

                    notes.setDenominationId(allocation.getDenominationId());
                    notes.setValue(value);
                    notes.setCount(denominationCount);

                    remainder = remainder - (value * denominationCount);

                    //add to returnValue denominationId value and count
                    returnValue.add(notes);
                }

            } else {
                break;
            }

        }
        return returnValue;
    }

    @Override
    public Integer updateClientAccount(String accountNumber, double amount) {

        return accountRepository.updateBalanceByAccountNumber(accountNumber, amount);
    }

    @Override
    public Integer updateAtm(int atmId, int denominationId, int count) {

        return atmAllocationRepository.updateAtmAllocationByAtmId(atmId, denominationId, count);
    }

    @Override
    public void saveTransaction(WithdrawModel withdrawModel) {

        double amount = 0;

        List<DispenseDto> withdrawal = getWithdrawalByAmount(withdrawModel.getAtmId(), withdrawModel.getAccountNumber(), withdrawModel.getAmount());

        for (DispenseDto dispense : withdrawal) {
            int countAtmUpdates = updateAtm(withdrawModel.getAtmId(), dispense.getDenominationId(), dispense.getCount());

            if (!(countAtmUpdates > 0))
                throw new AccountServiceException(AccountMessage.ATM_UPDATE_ERROR.message);

            amount += (dispense.getValue() * dispense.getCount());
        }

        int countAccountUpdates = updateClientAccount(withdrawModel.getAccountNumber(), amount);

        if (!(countAccountUpdates > 0))
            throw new AccountServiceException(AccountMessage.CLIENT_BALANCE_UPDATE_ERROR.message);
    }
}
