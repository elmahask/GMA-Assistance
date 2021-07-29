package com.gma.assistance.gma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "insurance_company")
public class InsuranceCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "contract_date")
    private Date contractDate;

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

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "policyNumber=" + policyNumber + '\'' +
                "referenceNumber=" + referenceNumber + '\'' +
                "contractDate=" + contractDate + '\'' +
                "countryId=" + countryId + '\'' +
                '}';
    }
}
