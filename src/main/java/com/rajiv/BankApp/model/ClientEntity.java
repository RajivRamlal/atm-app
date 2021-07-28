package com.rajiv.BankApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "client")
@Table(name = "client")
public class ClientEntity implements Serializable {

    private static final long serialVersionUID = -2976805117727567682L;

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "title", length = 10)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "client_sub_type_code", nullable = false)
    private String clientSubTypeCode;

    @OneToMany(mappedBy = "client",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<AccountEntity> accounts;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_sub_type_code",
            referencedColumnName = "client_sub_type_code", insertable = false, updatable = false)
    private ClientSubTypeEntity clientSubType;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    public ClientSubTypeEntity getClientSubType() {
        return clientSubType;
    }

    public void setClientSubType(ClientSubTypeEntity clientSubType) {
        this.clientSubType = clientSubType;
    }
}
