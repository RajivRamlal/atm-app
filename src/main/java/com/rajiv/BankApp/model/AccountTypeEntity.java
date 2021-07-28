package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "account_type")
@Table(name = "account_type")
public class AccountTypeEntity implements Serializable {

    private static final long serialVersionUID = 1516769470857282128L;

    @Id
    @Column(name = "account_type_code", length = 10)
    private String accountTypeCode;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "transactional")
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
