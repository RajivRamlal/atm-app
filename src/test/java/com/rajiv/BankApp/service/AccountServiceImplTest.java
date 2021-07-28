package com.rajiv.BankApp.service;

import com.rajiv.BankApp.dto.AccountDto;
import com.rajiv.BankApp.dto.AtmDto;
import com.rajiv.BankApp.dto.DispenseDto;
import com.rajiv.BankApp.dto.shared.AccountCurrency;
import com.rajiv.BankApp.dto.shared.AccountType;
import com.rajiv.BankApp.exceptions.AccountServiceException;
import com.rajiv.BankApp.model.*;
import com.rajiv.BankApp.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Autowired
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @Mock
    private AtmRepository atmRepository;

    @Mock
    private AtmAllocationRepository atmAllocationRepository;

    @Mock
    private DenominationRepository denominationRepository;

    @Test
    final void getAccountByClientId() {
        int clientId = 1;

        List<AccountEntity> accounts = new ArrayList<>();

        AccountEntity account1 = new AccountEntity();
        account1.setClientAccountNumber("1053664521");
        account1.setClientId(1);
        account1.setAccountTypeCode(AccountType.SAVINGS.accType);
        account1.setCurrencyCode(AccountCurrency.SOUTH_AFRICAN_RAND.currency);
        account1.setDisplayBalance(10612.09);

        AccountEntity account2 = new AccountEntity();
        account2.setClientAccountNumber("1041444501");
        account2.setClientId(2);
        account2.setAccountTypeCode(AccountType.SAVINGS.accType);
        account2.setCurrencyCode(AccountCurrency.SOUTH_AFRICAN_RAND.currency);
        account2.setDisplayBalance(1803.76);

        accounts.add(account1);
        accounts.add(account2);

        AccountTypeEntity accountType = new AccountTypeEntity();
        accountType.setAccountTypeCode(AccountType.SAVINGS.accType);
        accountType.setDescription("Savings Account");
        accountType.setTransactional(true);

        when(accountRepository.findByClientId(anyInt())).thenReturn(accounts);
        when(accountTypeRepository.findByAccountTypeCode(anyString())).thenReturn(accountType);

        List<AccountDto> accountDtoList = accountService.getAccountByClientId(clientId);
        assertNotNull(accountDtoList);

        assertEquals(2, accountDtoList.size());

    }

    @Test
    final void getAccountByClientIdException() {
        int clientId = 1;

        Assertions.assertThrows(AccountServiceException.class, () -> {
            List<AccountDto> accountDtoList = accountService.getAccountByClientId(clientId);
        });
    }

    @Test
    final void getAccountWithdrawal() {
        int atmId = 1;
        int clientId = 1;
        String accountNumber = "1053664521";

        AccountEntity account = new AccountEntity();
        account.setClientAccountNumber("1053664521");
        account.setClientId(1);
        account.setAccountTypeCode(AccountType.SAVINGS.accType);
        account.setCurrencyCode(AccountCurrency.SOUTH_AFRICAN_RAND.currency);
        account.setDisplayBalance(10612.09);

        AccountTypeEntity accountType = new AccountTypeEntity();
        accountType.setAccountTypeCode(AccountType.SAVINGS.accType);
        accountType.setDescription("Savings Account");
        accountType.setTransactional(true);

        when(accountRepository.findByClientAccountNumberAndClientId(anyString(), anyInt())).thenReturn(account);
        when(accountTypeRepository.findByAccountTypeCode(anyString())).thenReturn(accountType);

        AccountDto withdrawal = accountService.getAccountWithdrawal(atmId, clientId, accountNumber);
        assertNotNull(withdrawal);

        assertEquals(accountNumber, withdrawal.getClientAccountNumber());
    }

    @Test
    final void getAtmById() {
        int atmId = 1;

        AtmEntity atm = new AtmEntity();

        atm.setAtmId(1);
        atm.setName("SANDTON1");
        atm.setLocation("Sandton City Shopping Centre");

        List<AtmAllocationEntity> atmAllocation = new ArrayList<>();

        AtmAllocationEntity allocation1 = new AtmAllocationEntity();
        allocation1.setAtmAllocationId(1);
        allocation1.setAtmId(1);
        allocation1.setDenominationId(1);
        allocation1.setCount(50);

        AtmAllocationEntity allocation2 = new AtmAllocationEntity();
        allocation2.setAtmAllocationId(1);
        allocation2.setAtmId(1);
        allocation2.setDenominationId(2);
        allocation2.setCount(5);

        atmAllocation.add(allocation1);
        atmAllocation.add(allocation2);

        atm.setAllocations(atmAllocation);

        when(atmRepository.findByAtmId(anyInt())).thenReturn(atm);

        AtmDto atmDto = accountService.getAtmById(atmId);
        assertNotNull(atmDto);

        assertEquals(atmId, atmDto.getAtmId());
    }

    @Test
    final void getAtmByIdException() {
        int atmId = 1;

        Assertions.assertThrows(AccountServiceException.class, () -> {
            AtmDto atmDto = accountService.getAtmById(atmId);
        });
    }

    @Test
    final void getWithdrawalByAmount() {
        int atmId = 1;
        String accountNumber = "4030026880";
        double amount = 175.10;

        AccountEntity clientAccount = new AccountEntity();

        clientAccount.setClientAccountNumber("4030026880");
        clientAccount.setClientId(4);
        clientAccount.setAccountTypeCode(AccountType.CHEQUE.accType);
        clientAccount.setCurrencyCode(AccountCurrency.SOUTH_AFRICAN_RAND.currency);
        clientAccount.setDisplayBalance(19440.89);

        CreditCardLimitEntity creditCard = new CreditCardLimitEntity();

        creditCard.setClientAccountNumber("5027913218");
        creditCard.setAccountLimit(25000);

        AtmEntity atm = new AtmEntity();

        atm.setAtmId(1);
        atm.setName("SANDTON1");
        atm.setLocation("Sandton City Shopping Centre");

        List<AtmAllocationEntity> atmAllocation = new ArrayList<>();
        AtmAllocationEntity allocation = new AtmAllocationEntity();

        allocation.setAtmAllocationId(1);
        allocation.setAtmId(1);
        allocation.setDenominationId(1);
        allocation.setCount(50);

        atmAllocation.add(allocation);

        atm.setAllocations(atmAllocation);

        DenominationEntity denomination = new DenominationEntity();

        denomination.setDenominationId(1);
        denomination.setValue(10);
        denomination.setDenominationTypeCode('N');

        when(accountRepository.findByClientAccountNumber(anyString())).thenReturn(clientAccount);
        when(atmRepository.findByAtmId(anyInt())).thenReturn(atm);
        when(denominationRepository.findByDenominationIdAndDenominationTypeCode(anyInt(), anyChar())).thenReturn(denomination);

        List<DispenseDto> dispenseDtoList = accountService.getWithdrawalByAmount(atmId, accountNumber, amount);
        assertNotNull(dispenseDtoList);

        Double dispencedAmount = dispenseDtoList.stream()
                .mapToDouble(e -> e.getValue() * e.getCount())
                .sum();

        assertEquals(170, dispencedAmount);
    }

    @Test
    final void updateClientAccount() {
        String accountNumber = "4030026880";
        double amount = 150;

        when(accountRepository.updateBalanceByAccountNumber(anyString(), anyDouble())).thenReturn(1);

        int updatedRows = accountService.updateClientAccount(accountNumber, amount);
        assertEquals(1, updatedRows);
    }

    @Test
    final void updateAtm() {
        int atmId = 1;
        int denominationId = 1;
        int count = 5;

        when(atmAllocationRepository.updateAtmAllocationByAtmId(anyInt(), anyInt(), anyInt())).thenReturn(1);

        int updatedRows = accountService.updateAtm(atmId, denominationId, count);
        assertEquals(1, updatedRows);
    }
}