package com.htp.controller.command.util;

public class CommandException extends Exception {
    private static final long serialVersionUID = 1L;

    public CommandException(){
        super();
    }

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Exception ex){
        super(message, ex);
    }
}