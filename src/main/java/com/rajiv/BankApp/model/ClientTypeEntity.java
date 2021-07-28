package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "client_type")
@Table(name = "client_type")
public class ClientTypeEntity implements Serializable {

    private static final long serialVersionUID = -2120895848716086465L;

    @Id
    @GeneratedValue
    @Column(name = "client_type_code", length = 2)
    private String clientTypeCode;

    @Column(name = "description", nullable = false)
    private String description;

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
