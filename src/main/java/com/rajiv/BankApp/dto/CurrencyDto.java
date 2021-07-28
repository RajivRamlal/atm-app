package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class CurrencyDto implements Serializable {

    private static final long serialVersionUID = 25530055377203754L;

    private String currencyCode;
    private int decimalPlaces;
    private String description;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
