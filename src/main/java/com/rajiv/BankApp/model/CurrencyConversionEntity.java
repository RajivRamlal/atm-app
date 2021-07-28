package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "currency_conversion_rate")
@Table(name = "currency_conversion_rate")
public class CurrencyConversionEntity implements Serializable {

    private static final long serialVersionUID = -1730058154362130176L;

    @Id
    @GeneratedValue
    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "conversion_indicator", nullable = false, length = 1)
    private char conversionIndicator;

    @Column(name = "rate", nullable = false)
    private double rate;

    @OneToOne
    @JoinColumn(name = "currency_code",
            referencedColumnName = "currency_code", insertable = false, updatable = false)
    private CurrencyEntity currency;

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

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }
}
