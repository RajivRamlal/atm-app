package com.rajiv.BankApp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClientDto implements Serializable {

    private static final long serialVersionUID = 2321570754486640531L;

    private int clientId;
    private String title;
    private String name;
    private String surname;
    private Date dob;
    private String clientSubTypeCode;
    private List<AccountDto> accounts;
    private ClientSubTypeDto clientSubType;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public ClientSubTypeDto getClientSubType() {
        return clientSubType;
    }

    public void setClientSubType(ClientSubTypeDto clientSubType) {
        this.clientSubType = clientSubType;
    }
}
