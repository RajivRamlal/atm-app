package com.rajiv.BankApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "denomination_type")
@Table(name = "denomination_type")
public class DenominationTypeEntity implements Serializable {

    private static final long serialVersionUID = 4831313423312306589L;

    @Id
    @Column(name = "denomination_type_code", nullable = false, length = 1)
    private String denominationTypeCode;

    @Column(name = "description", nullable = false)
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
