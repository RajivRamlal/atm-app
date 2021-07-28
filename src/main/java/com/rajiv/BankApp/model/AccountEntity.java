package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "client_account")
@Table(name = "client_account")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = -3178487015679452903L;

    @Id
    @Column(name = "client_account_number", length = 10)
    private String clientAccountNumber;

    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Column(name = "account_type_code", nullable = false, length = 10)
    private String accountTypeCode;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "display_balance")
    private double displayBalance;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "client_id",
            referencedColumnName = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_type_code",
            referencedColumnName = "account_type_code", insertable = false, updatable = false)
    private AccountTypeEntity accountType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_code",
            referencedColumnName = "currency_code", insertable = false, updatable = false)
    private CurrencyEntity currency;

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

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public AccountTypeEntity getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEntity accountType) {
        this.accountType = accountType;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }
}
