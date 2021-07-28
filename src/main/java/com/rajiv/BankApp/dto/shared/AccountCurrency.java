package com.rajiv.BankApp.dto.shared;

public enum AccountCurrency {
    SOUTH_AFRICAN_RAND("ZAR");

    public final String currency;

    AccountCurrency(String currency) {
        this.currency = currency;
    }
}
