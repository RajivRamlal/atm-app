package com.rajiv.BankApp.dto;

import com.rajiv.BankApp.model.DenominationTypeEntity;

import java.io.Serializable;

public class DenominationDto implements Serializable {

    private static final long serialVersionUID = 7568403779361102566L;

    private int denominationId;
    private double value;
    private char denominationTypeCode;
    private DenominationTypeEntity denominationType;

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

    public char getDenominationTypeCode() {
        return denominationTypeCode;
    }

    public void setDenominationTypeCode(char denominationTypeCode) {
        this.denominationTypeCode = denominationTypeCode;
    }

    public DenominationTypeEntity getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(DenominationTypeEntity denominationType) {
        this.denominationType = denominationType;
    }
}
