package com.htp.service.validator;

import com.htp.domain.to.UserRoles;

import static com.htp.domain.to.UserRolesNamesInterface.*;

public class UserRolesValidator implements ValidatorInterface<UserRoles> {

    private static final ValidatorInterface<UserRoles> instance = new UserRolesValidator();

    private UserRolesValidator() {
    }

    public static ValidatorInterface<UserRoles> getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(UserRoles entity) {
        if (entity.getRoleName().equals(ADMIN) || entity.getRoleName().equals(USER) || entity.getRoleName().equals(GUEST)) {
            return true;
        } else {
            return false;
        }
    }
}
