package com.htp.service.validator;

import com.htp.domain.to.UserFavoriteNumber;

import static com.htp.service.validator.ValidationRegEx.REGEX_DATE_FORMAT;

public class UserFavoriteNumberValidatir implements ValidatorInterface<UserFavoriteNumber> {


    private static final ValidatorInterface<UserFavoriteNumber> instance = new UserFavoriteNumberValidatir();

    public UserFavoriteNumberValidatir() {
    }

    public static ValidatorInterface<UserFavoriteNumber> getInstance() {
        return instance;
    }


    @Override
    public boolean isValid(UserFavoriteNumber entity) {
        boolean dateValid;
        dateValid=entity.getDate().matches(REGEX_DATE_FORMAT);
        return dateValid;
    }
}
