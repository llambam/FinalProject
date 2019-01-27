package com.htp.domain.to;

import java.io.Serializable;
import java.util.Objects;

public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long userId;
    private String roleName;

    public UserRoles() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserRoles(Long userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(userId, userRoles.userId) &&
                Objects.equals(roleName, userRoles.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleName);
    }


}