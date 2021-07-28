package com.rajiv.BankApp.service;

import com.rajiv.BankApp.dto.*;
import com.rajiv.BankApp.request.WithdrawModel;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAccountByClientId(int id);
    CurrencyDto getCurrencyByCode(String code);
    CurrencyConversionDto getCurrConversionByCode(String code);
    List<CurrencyAccountDto> getCurrencyAccountByClientId(int id);
    AccountDto getAccountWithdrawal(int atmId, int clientId, String accountNumber);
    AtmDto getAtmById(int atmId);
    List<DispenseDto> getWithdrawalByAmount(int atmId, String accountNumber, double amount);
    List<DispenseDto> getAtmDispense(int atmId, double amount);
    Integer updateClientAccount(String accountNumber, double balance);
    Integer updateAtm(int atmId, int denominationId, int count);
    void saveTransaction(WithdrawModel withdrawModel);
}
