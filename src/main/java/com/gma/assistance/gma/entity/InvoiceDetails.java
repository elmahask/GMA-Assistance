package com.gma.assistance.gma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "invoice_time", nullable = false)
    private Date invoiceTime;

    @Column(name = "payment_type_id", nullable = false)
    private Integer paymentTypeId;

    @Column(name = "provider_id", nullable = false)
    private Integer providerId;

    @Column(name = "patient_ic_reference", nullable = false)
    private Integer patientIcReference;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setPatientIcReference(Integer patientIcReference) {
        this.patientIcReference = patientIcReference;
    }

    public Integer getPatientIcReference() {
        return patientIcReference;
    }

    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "id=" + id + '\'' +
                "value=" + value + '\'' +
                "invoiceTime=" + invoiceTime + '\'' +
                "paymentTypeId=" + paymentTypeId + '\'' +
                "providerId=" + providerId + '\'' +
                "patientIcReference=" + patientIcReference + '\'' +
                '}';
    }
}
