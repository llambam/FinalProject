package com.htp.exception;

public class NoSuchEntityException extends Exception {
//сделать экстендс не от эксепшн а от ран тайм эксепшн
    private static final long serialVersionUID = 1L;

    public NoSuchEntityException(String message){
        super(message);
    }

    public NoSuchEntityException(String message, Exception ex){
        super(message, ex);
    }
}