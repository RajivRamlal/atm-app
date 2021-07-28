package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class AccountTypeDto implements Serializable {

    private static final long serialVersionUID = -1385045755891701070L;

    private String accountTypeCode;
    private String description;
    private boolean transactional;

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }
}
