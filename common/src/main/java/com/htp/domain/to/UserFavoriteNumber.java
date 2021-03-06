package com.htp.domain.to;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class UserFavoriteNumber implements Serializable {
    private Long userID;
    private Long phoneBookID;
    private Date date;

    public UserFavoriteNumber() {
    }

    public UserFavoriteNumber(Long userID, Long phoneBookID, Date date) {
        this.userID = userID;
        this.phoneBookID = phoneBookID;
        this.date = date;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPhoneBookID() {
        return phoneBookID;
    }

    public void setPhoneBookID(Long phoneBookID) {
        this.phoneBookID = phoneBookID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserFavoriteNumber{" +
                "userID=" + userID +
                ", phoneBookID=" + phoneBookID +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavoriteNumber that = (UserFavoriteNumber) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(phoneBookID, that.phoneBookID) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, phoneBookID, date);
    }
}
