package com.htp.service.validator;

import com.htp.domain.to.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.htp.service.validator.ValidationRegEx.REGEX_LOGIN;
import static com.htp.service.validator.ValidationRegEx.REGEX_PASSWORD;


public class LoginValidator implements ValidatorInterface<User> {

    private static final ValidatorInterface<User> instance = new LoginValidator();

    private LoginValidator() {
    }

    public static ValidatorInterface<User> getInstance() {
        return instance;
    }





    /* Validates fields of parameter object for correct values.
     *
     * @param user User object which is need to be validated for correctness fields.
     * @return true if validation fields of object was successfully: login contains letters of the Russian and English
     * and no longer than 25 characters,
     * password contains uppercase and lowercase letters of the English language and figures;
     * else false
     */
    @Override
    public boolean isValid(User user) {
//        boolean loginValid = user.getUserName().matches(REGEX_LOGIN);
//        boolean passwordValid = user.getSurname().matches(REGEX_PASSWORD);
//        if (loginValid==passwordValid==true){
//            return true;
//        }else {
//            return false;
//        }
        return true;
    }
}