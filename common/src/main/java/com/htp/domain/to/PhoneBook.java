package com.htp.domain.to;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PhoneBook implements Serializable {
    private Long phoneBookId;
    private String name;
    private String surname;
    private int telephone;
    private String eMail;
    private Date creationDate;

    public PhoneBook(Long phoneBookId, String name, String surname, int telephone, String eMail, Date creationDate) {
        this.phoneBookId = phoneBookId;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.eMail = eMail;
        this.creationDate = creationDate;
    }

    public PhoneBook() {
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
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

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookId=" + phoneBookId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone=" + telephone +
                ", eMail='" + eMail + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return telephone == phoneBook.telephone &&
                Objects.equals(phoneBookId, phoneBook.phoneBookId) &&
                Objects.equals(name, phoneBook.name) &&
                Objects.equals(surname, phoneBook.surname) &&
                Objects.equals(eMail, phoneBook.eMail) &&
                Objects.equals(creationDate, phoneBook.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBookId, name, surname, telephone, eMail, creationDate);
    }
}
