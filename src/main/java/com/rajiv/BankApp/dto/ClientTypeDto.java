package com.rajiv.BankApp.dto;

public class ClientTypeDto {

    private static final long serialVersionUID = 3435602381936617892L;

    private String clientTypeCode;
    private String description;

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
}
