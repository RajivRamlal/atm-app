package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "atm_allocation")
@Table(name = "atm_allocation")
public class AtmAllocationEntity implements Serializable {

    private static final long serialVersionUID = 4787884952197643609L;

    @Id
    @GeneratedValue
    @Column(name = "atm_allocation_id")
    private int atmAllocationId;

    @Column(name = "atm_id", nullable = false)
    private int atmId;

    @Column(name = "denomination_id", nullable = false)
    private int denominationId;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "atm_id",
            referencedColumnName = "atm_id", insertable = false, updatable = false)
    private AtmEntity atm;

    @OneToMany
    @JoinColumn(name = "denomination_id",
            referencedColumnName = "denomination_id", insertable = false, updatable = false)
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
