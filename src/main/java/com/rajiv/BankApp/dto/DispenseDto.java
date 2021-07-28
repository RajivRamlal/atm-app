package com.rajiv.BankApp.dto;

import java.io.Serializable;

public class DispenseDto implements Serializable {

    private static final long serialVersionUID = 3370520208872419249L;

    private int denominationId;
    private double value;
    private int count;

    public int getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(int denominationId) {
        this.denominationId = denominationId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
