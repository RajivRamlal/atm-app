package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "atm")
@Table(name = "atm")
public class AtmEntity implements Serializable {

    private static final long serialVersionUID = -2305306868261506218L;

    @Id
    @GeneratedValue
    @Column(name = "atm_id")
    private int atmId;

    @Column(name = "name", nullable = false, length = 10, unique = true)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "atm",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
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
