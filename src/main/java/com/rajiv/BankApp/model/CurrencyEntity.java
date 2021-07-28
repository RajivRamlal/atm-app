package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "currency")
@Table(name = "currency")
public class CurrencyEntity implements Serializable {

    private static final long serialVersionUID = 4756636943769760237L;

    @Id
    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "decimal_places", nullable = false)
    private int decimalPlaces;

    @Column(name = "description", nullable = false)
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
