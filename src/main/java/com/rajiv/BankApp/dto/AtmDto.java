package com.rajiv.BankApp.dto;

import com.rajiv.BankApp.model.AtmAllocationEntity;

import java.io.Serializable;
import java.util.List;

public class AtmDto implements Serializable {

    private static final long serialVersionUID = 2157113850187593884L;

    private int atmId;
    private String name;
    private String location;
    private List<AtmAllocationEntity> allocations;

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<AtmAllocationEntity> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<AtmAllocationEntity> allocations) {
        this.allocations = allocations;
    }
}
