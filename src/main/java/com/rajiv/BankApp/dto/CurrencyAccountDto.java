package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class CurrencyAccountDto implements Serializable {

    private static final long serialVersionUID = -4400034870036921893L;

    private String accountNumber;
    private String currencyCode;
    private double accountBalance;
    private double conversionRate;
    private double convertedAmount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double accountBalance, double conversionRate) {
        this.convertedAmount = (accountBalance * conversionRate);
    }
}
