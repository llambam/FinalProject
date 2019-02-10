package com.htp.service.validator;

import com.htp.exception.ServiceException;

public class ValidationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Exception ex) {
        super(message, ex);
    }
}