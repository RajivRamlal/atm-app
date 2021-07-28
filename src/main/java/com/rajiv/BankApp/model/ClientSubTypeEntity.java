package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "client_sub_type")
@Table(name = "client_sub_type")
public class ClientSubTypeEntity implements Serializable {

    private static final long serialVersionUID = -4438478547642655881L;

    @Id
    @GeneratedValue
    @Column(name = "client_sub_type_code", length = 4)
    private String clientSubTypeCode;

    @Column(name = "client_type_code", nullable = false, length = 2)
    private String clientTypeCode;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_type_code",
            referencedColumnName = "client_type_code", insertable = false, updatable = false)
    private ClientTypeEntity clientType;

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

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

    public ClientTypeEntity getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeEntity clientType) {
        this.clientType = clientType;
    }
}
