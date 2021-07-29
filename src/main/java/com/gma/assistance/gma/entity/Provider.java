package com.gma.assistance.gma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contract_date")
    private Date contractDate;

    @Column(name = "provider_status_id", nullable = false)
    private Integer providerStatusId;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setProviderStatusId(Integer providerStatusId) {
        this.providerStatusId = providerStatusId;
    }

    public Integer getProviderStatusId() {
        return providerStatusId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "contractDate=" + contractDate + '\'' +
                "providerStatusId=" + providerStatusId + '\'' +
                "countryId=" + countryId + '\'' +
                '}';
    }
}
