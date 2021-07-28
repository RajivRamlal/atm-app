package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "denomination")
@Table(name = "denomination")
public class DenominationEntity implements Serializable {

    private static final long serialVersionUID = 6726636921193561568L;

    @Id
    @GeneratedValue
    @Column(name = "denomination_id", nullable = false)
    private int denominationId;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "denomination_type_code", nullable = false, length = 1)
    private char denominationTypeCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "denomination_type_code",
            referencedColumnName = "denomination_type_code", insertable = false, updatable = false)
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
