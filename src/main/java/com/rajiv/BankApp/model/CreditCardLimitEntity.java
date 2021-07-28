package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "credit_card_limit")
@Table(name = "credit_card_limit")
public class CreditCardLimitEntity implements Serializable {

    private static final long serialVersionUID = -5683109562230164326L;

    @Id
    @Column(length = 10)
    private String clientAccountNumber;

    @Column(name = "account_limit")
    private double accountLimit;

    @OneToOne
    @JoinColumn(name = "client_account_number",
            referencedColumnName = "client_account_number", insertable = false, updatable = false)
    private AccountEntity account;

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
