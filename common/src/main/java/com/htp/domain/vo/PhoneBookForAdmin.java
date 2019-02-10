package com.htp.domain.vo;

import java.sql.Date;
import java.util.Objects;

public class PhoneBookForAdmin {

    private Long phoneBookId;
    private Long userID;
    private String name;
    private String surname;
    private String telephone;
    private String eMail;
    private String city;
    private String district;
    private String street;
    private int houseNumber;
    private int floor;
    private int apartmentNumber;
    private Date creationDate;


    public PhoneBookForAdmin(Long phoneBookId, String name, String surname, String telephone, String eMail, Date creationDate, Long userID, String city, String district, String street, int houseNumber, int floor, int apartmentNumber) {
        this.phoneBookId = phoneBookId;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.eMail = eMail;
        this.creationDate = creationDate;
        this.userID = userID;
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
    }

    public PhoneBookForAdmin() {
    }


    public Long getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(Long phoneBookId) {
        this.phoneBookId = phoneBookId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBookForAdmin that = (PhoneBookForAdmin) o;
        return houseNumber == that.houseNumber &&
                floor == that.floor &&
                apartmentNumber == that.apartmentNumber &&
                Objects.equals(phoneBookId, that.phoneBookId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(eMail, that.eMail) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(city, that.city) &&
                Objects.equals(district, that.district) &&
                Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBookId, name, surname, telephone, eMail, creationDate, userID, city, district, street, houseNumber, floor, apartmentNumber);
    }

    @Override
    public String toString() {
        return "PhoneBookForAdmin{" +
                "phoneBookId=" + phoneBookId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", eMail='" + eMail + '\'' +
                ", creationDate=" + creationDate +
                ", userID=" + userID +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", floor=" + floor +
                ", apartmentNumber=" + apartmentNumber +
                '}';
    }
}
