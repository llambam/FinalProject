package com.htp.service.validator;

import com.htp.domain.to.Adress;

import static com.htp.service.validator.ValidationRegEx.REGEX_ADRESS;

public class AdressValidator implements ValidatorInterface<Adress> {


    private static final ValidatorInterface<Adress> instance = new AdressValidator();

    public AdressValidator() {
    }

    public static ValidatorInterface<Adress> getInstance() {
        return instance;
    }


    @Override
    public boolean isValid(Adress entity) {
        boolean city;
        boolean district;
        boolean street;

        city = entity.getCity().matches(REGEX_ADRESS);
        district = entity.getDistrict().matches(REGEX_ADRESS);
        street = entity.getStreet().matches(REGEX_ADRESS);

        if (city == district == street == true) {
            return true;
        } else {
            return false;
        }
    }
}
