package com.htp.service.validator;

import com.htp.domain.to.User;

import java.util.regex.Pattern;

import static com.htp.service.validator.ValidationRegEx.*;

public class UserValidator implements ValidatorInterface<User> {

    private static final ValidatorInterface<User> instance = new UserValidator();

    private UserValidator() {
    }

    public static ValidatorInterface<User> getInstance() {
        return instance;
    }


    private static final Pattern patternEmail = Pattern.compile(REGEX_EMAIL);

    @Override
    public boolean isValid(User entity) {
        boolean nameValid;
        boolean surnameValid;
        boolean telephoneValid;
        boolean eMailValid;
        boolean block;
        boolean dateValid;

        nameValid = entity.getUserName().matches(REGEX_NAME_AND_SURNAME);
        surnameValid = entity.getSurname().matches(REGEX_NAME_AND_SURNAME);
        telephoneValid = entity.getTelephone().length() == TELEPHONE_LENGTH;
        eMailValid = entity.getEmail().matches(REGEX_EMAIL);
        block=entity.getBlocked()==0|entity.getBlocked()==1;
        dateValid = entity.getRegistrationDate().matches(REGEX_DATE_FORMAT);

        if(nameValid==surnameValid==telephoneValid==eMailValid==block==dateValid==true){
            return true;
        }else {
            return false;
        }
    }
}
