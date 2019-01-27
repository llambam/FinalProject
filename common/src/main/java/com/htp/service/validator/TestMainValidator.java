package com.htp.service.validator;

import static com.htp.service.validator.ValidationRegEx.REGEX_NAME_AND_SURNAME;

public class TestMainValidator {

    public static void main(String[] args) {

String ss="1111111111111";
        boolean telephoneValid = ss.length() == 13;
        System.out.println(telephoneValid);

    }

}
