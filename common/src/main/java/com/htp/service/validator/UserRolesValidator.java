package com.htp.service.validator;

import com.htp.domain.to.UserRoles;

public class UserRolesValidator implements ValidatorInterface<UserRoles>{

    private static final ValidatorInterface<UserRoles> instance = new UserRolesValidator();

    private UserRolesValidator() {
    }
    public static ValidatorInterface<UserRoles> getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(UserRoles entity) {


        return true;
    }
}
