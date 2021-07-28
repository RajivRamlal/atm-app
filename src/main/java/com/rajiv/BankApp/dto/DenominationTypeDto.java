package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class DenominationTypeDto implements Serializable {

    private static final long serialVersionUID = -4857997527823861493L;

    private String denominationTypeCode;
    private String description;

    public String getDenominationTypeCode() {
        return denominationTypeCode;
    }

    public void setDenominationTypeCode(String denominationTypeCode) {
        this.denominationTypeCode = denominationTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
