package com.htp.service.validator;

public interface ValidationRegEx {
    int TELEPHONE_LENGTH = 13;

    String REGEX_LOGIN = "[a-zA-Z,0-9]{2,25}";
    String REGEX_PASSWORD = "[a-zA-Z,0-9]{2,25}";
    String REGEX_EMAIL="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
    String REGEX_NAME_AND_SURNAME="[a-zA-Z]{2,40}";
    String REGEX_ADRESS="[a-zA-Z,0-9]{2,40}";
    String REGEX_DATE_FORMAT="^\\d{4}-\\d{2}-\\d{2}$"; // "YYYY-MM-DD"
}
