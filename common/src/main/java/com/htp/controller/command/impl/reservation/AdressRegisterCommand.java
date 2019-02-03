package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.AdressDao;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.service.AdressService;
import com.htp.service.impl.AdressServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdressRegisterCommand implements CommandInterface, PagePath {

    private static final AdressService SERVICE_USER = AdressServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final AdressDao adressDao = factory.getAdressDao();
    private static final UserDao userDao = factory.getUserDao();

    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String BLOCK = "block";
    private static final String REGISRATION_DATE = "date";
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;
    private static final Integer DEFAULT_BLOCK_VALUE = 0;


    public AdressRegisterCommand() {
    }
    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdressRegisterCommand();
    }



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        return null;
    }
}
