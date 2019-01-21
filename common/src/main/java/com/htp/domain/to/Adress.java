package com.htp.domain.to;

import java.io.Serializable;
import java.util.Objects;

public class Adress implements Serializable {

    private Long phoneBookID;
    private Long userID;
    private String city;
    private String district;
    private String street;
    private int houseNumber;
    private int floor;
    private int apartmentNumber;

    public Adress() {
    }

    public Adress(Long phoneBookID, Long userID, String city, String district, String street, int houseNumber, int floor, int apartmentNumber) {
        this.phoneBookID = phoneBookID;
        this.userID = userID;
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
    }

    public Long getPhoneBookID() {
        return phoneBookID;
    }

    public void setPhoneBookID(Long phoneBookID) {
        this.phoneBookID = phoneBookID;
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
        Adress adress = (Adress) o;
        return houseNumber == adress.houseNumber &&
                floor == adress.floor &&
                apartmentNumber == adress.apartmentNumber &&
                Objects.equals(phoneBookID, adress.phoneBookID) &&
                Objects.equals(userID, adress.userID) &&
                Objects.equals(city, adress.city) &&
                Objects.equals(district, adress.district) &&
                Objects.equals(street, adress.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBookID, userID, city, district, street, houseNumber, floor, apartmentNumber);
    }

    @Override
    public String toString() {
        return "Adress{" +
                "phoneBookID=" + phoneBookID +
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
