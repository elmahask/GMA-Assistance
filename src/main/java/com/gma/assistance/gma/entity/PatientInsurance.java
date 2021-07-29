package com.gma.assistance.gma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "patient_insurance")
public class PatientInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "note")
    private String note;

    @Column(name = "reason")
    private String reason;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "insurance_company_id", nullable = false)
    private Integer insuranceCompanyId;

    @Column(name = "patient_ic_reference", nullable = false)
    private Integer patientIcReference;

    @Column(name = "case_type_id", nullable = false)
    private Integer caseTypeId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setInsuranceCompanyId(Integer insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Integer getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setPatientIcReference(Integer patientIcReference) {
        this.patientIcReference = patientIcReference;
    }

    public Integer getPatientIcReference() {
        return patientIcReference;
    }

    public void setCaseTypeId(Integer caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public Integer getCaseTypeId() {
        return caseTypeId;
    }

    @Override
    public String toString() {
        return "PatientInsurance{" +
                "id=" + id + '\'' +
                "note=" + note + '\'' +
                "reason=" + reason + '\'' +
                "registerDate=" + registerDate + '\'' +
                "insuranceCompanyId=" + insuranceCompanyId + '\'' +
                "patientIcReference=" + patientIcReference + '\'' +
                "caseTypeId=" + caseTypeId + '\'' +
                '}';
    }
}
