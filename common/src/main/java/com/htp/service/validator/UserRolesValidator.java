package com.htp.service.validator;

import com.htp.domain.to.UserRoles;

public class UserRolesValidator implements ValidatorInterface<UserRoles> {

    private static final ValidatorInterface<UserRoles> instance = new UserRolesValidator();

    private UserRolesValidator() {
    }

    public static ValidatorInterface<UserRoles> getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(UserRoles entity) {
        String admin = "ADMIN";
        String user = "USER";
        String guest = "GUEST";
        if (entity.getRoleName() == admin | entity.getRoleName() == user | entity.getRoleName() == guest) {
            return true;
        } else {
            return false;
        }
    }
}
