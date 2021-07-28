package com.rajiv.BankApp.dto;

public class ClientSubTypeDto {

    private static final long serialVersionUID = 8905094858878236967L;

    private String clientSubTypeCode;
    private String clientTypeCode;
    private String description;
    private ClientTypeDto clientType;

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientTypeDto getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeDto clientType) {
        this.clientType = clientType;
    }
}
