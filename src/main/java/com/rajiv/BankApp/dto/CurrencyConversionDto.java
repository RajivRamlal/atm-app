package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class CurrencyConversionDto implements Serializable {

    private static final long serialVersionUID = -1236025195828346555L;

    private String currencyCode;
    private char conversionIndicator;
    private double rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public char getConversionIndicator() {
        return conversionIndicator;
    }

    public void setConversionIndicator(char conversionIndicator) {
        this.conversionIndicator = conversionIndicator;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
