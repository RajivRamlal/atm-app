package com.rajiv.BankApp.dto;

import com.rajiv.BankApp.model.AtmEntity;
import com.rajiv.BankApp.model.DenominationEntity;

import java.io.Serializable;
import java.util.List;

public class AtmAllocationDto implements Serializable {

    private static final long serialVersionUID = 2153089374731205911L;

    private int atmAllocationId;
    private int atmId;
    private int denominationId;
    private int count;
    private AtmEntity atm;
    private List<DenominationEntity> denominations;

    public int getAtmAllocationId() {
        return atmAllocationId;
    }

    public void setAtmAllocationId(int atmAllocationId) {
        this.atmAllocationId = atmAllocationId;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public int getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(int denominationId) {
        this.denominationId = denominationId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AtmEntity getAtm() {
        return atm;
    }

    public void setAtm(AtmEntity atm) {
        this.atm = atm;
    }

    public List<DenominationEntity> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<DenominationEntity> denominations) {
        this.denominations = denominations;
    }
}
