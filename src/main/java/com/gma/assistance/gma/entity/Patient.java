package com.gma.assistance.gma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ic_reference", nullable = false)
    private Integer icReference;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "roomNo")
    private String roomNo;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    public void setIcReference(Integer icReference) {
        this.icReference = icReference;
    }

    public Integer getIcReference() {
        return icReference;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "icReference=" + icReference + '\'' +
                "fullName=" + fullName + '\'' +
                "birthdate=" + birthdate + '\'' +
                "gender=" + gender + '\'' +
                "hotelName=" + hotelName + '\'' +
                "roomNo=" + roomNo + '\'' +
                "nationality=" + nationality + '\'' +
                "countryId=" + countryId + '\'' +
                '}';
    }
}
