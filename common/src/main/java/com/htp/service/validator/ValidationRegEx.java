package com.htp.service.validator;

public interface ValidationRegEx {
    int TELEPHONE_LENGTH_MAX = 13;
    int TELEPHONE_LENGTH_MIN = 7;

    String REGEX_LOGIN = "[a-zA-Z0-9]{2,25}";
    String REGEX_PASSWORD = "[a-zA-Z0-9]{2,25}";
    String REGEX_EMAIL="^(|(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6})$";
    String REGEX_NAME_AND_SURNAME="[a-zA-Z0-9]{2,40}";
    String REGEX_ADRESS="[a-zA-Z0-9]{1,40}";
    String REGEX_DATE_FORMAT="^\\d{4}-\\d{2}-\\d{2}$"; // "YYYY-MM-DD"
}
