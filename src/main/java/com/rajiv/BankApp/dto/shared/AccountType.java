package com.rajiv.BankApp.dto.shared;

public enum AccountType {
    CHEQUE("CHQ"),
    SAVINGS("SVGS"),
    CREDIT_CARD("CCRD"),
    PERSONAL_LOAN("PLOAN"),
    HOME_LOAN("HLOAN"),
    CURRENCY_ACCOUNT("CFCA");

    public final String accType;

    AccountType(String accType) {
        this.accType = accType;
    }
}
