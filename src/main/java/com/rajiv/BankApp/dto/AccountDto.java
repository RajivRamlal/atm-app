package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class AccountDto implements Serializable {

    private static final long serialVersionUID = 3587245763382986596L;

    private String clientAccountNumber;
    private int clientId;
    private String accountTypeCode;
    private String accountType;
    private String currencyCode;
    private double displayBalance;
    private ClientDto client;
    private CurrencyDto currency;
    private int atmId;

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountDescription) {
        this.accountType = accountDescription;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(double displayBalance) {
        this.displayBalance = displayBalance;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDto currency) {
        this.currency = currency;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }
}
