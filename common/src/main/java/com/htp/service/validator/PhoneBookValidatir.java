package com.htp.service.validator;

import com.htp.domain.to.PhoneBook;

import static com.htp.service.validator.ValidationRegEx.*;

public class PhoneBookValidatir implements ValidatorInterface<PhoneBook> {


    private static final ValidatorInterface<PhoneBook> instance = new PhoneBookValidatir();

    private PhoneBookValidatir() {
    }

    public static ValidatorInterface<PhoneBook> getInstance() {
        return instance;
    }


    @Override
    public boolean isValid(PhoneBook entity) {

        boolean nameValid;
        boolean surnameValid;
        boolean telephoneValid;
        boolean eMailValid;
        boolean dateValid;

        nameValid = entity.getName().matches(REGEX_NAME_AND_SURNAME);
        surnameValid = entity.getSurname().matches(REGEX_NAME_AND_SURNAME);
        telephoneValid = entity.getTelephone().length() <= TELEPHONE_LENGTH_MAX & entity.getTelephone().length() >= TELEPHONE_LENGTH_MIN;
        eMailValid = entity.geteMail().matches(REGEX_EMAIL);
        dateValid = entity.getCreationDate().matches(REGEX_DATE_FORMAT);

        if(nameValid==surnameValid==telephoneValid==eMailValid==dateValid==true){
            return true;
        }else {
            return false;
        }
    }
}
