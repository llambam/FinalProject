package com.htp.domain.vo;

import java.sql.Date;
import java.util.Objects;

public class UserForAdminTable {
    private Long userId;
    private String userName;
    private String surname;
    private String login;
    private String password;
    private String telephone;
    private String email;
    private int blocked;
    private Date registrationDate;
    private String roleName;

    public UserForAdminTable() {
    }

    public UserForAdminTable(Long userId, String userName, String surname, String login, String password, String telephone, String email, int blocked, Date registrationDate, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.blocked = blocked;
        this.registrationDate = registrationDate;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForAdminTable that = (UserForAdminTable) o;
        return blocked == that.blocked &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, surname, login, password, telephone, email, blocked, registrationDate, roleName);
    }

    @Override
    public String toString() {
        return "UserForAdminTable{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", blocked=" + blocked +
                ", registrationDate=" + registrationDate +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
