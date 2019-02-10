package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.date.CurrentDate;
import com.htp.domain.date.DateInterface;
import com.htp.domain.to.User;
import com.htp.service.AdressService;
import com.htp.service.PhoneBookService;
import com.htp.service.UserRolesService;
import com.htp.service.UserService;
import com.htp.service.impl.AdressServiceImpl;
import com.htp.service.impl.PhoneBookServiceImpl;
import com.htp.service.impl.UserRolesServiceImpl;
import com.htp.service.impl.UserServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminEditUserCommand implements PagePath, CommandInterface {
    private static final UserService SERVICE_USER = UserServiceImpl.getInstance();
    private static final UserRolesService SERVICE_USER_ROLES = UserRolesServiceImpl.getInstance();
    private static final AdressService SERVICE_ADRES = AdressServiceImpl.getInstance();
    private static final PhoneBookService SERVICE_PHONE_BOOK = PhoneBookServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final UserDao userDao = factory.getUserDao();
    private static final DateInterface SERVICE_DATE = CurrentDate.getInstance();


    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";

    private static final String USER_ID_FOR_ADMIN = "userIdFA";
    private static final String NAME = "userName";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String BLOCK = "block";
    private static final String REGISRATION_DATE = "date";
    private static final String CITY = "city";
    private static final String DISTRICT = "district";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";
    private static final String FLOOR = "floor";
    private static final String APARTMENT_NUMBER = "apartment_number";


    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;
    private static final Integer DEFAULT_BLOCK_VALUE = 0;

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminEditUserCommand();
    }

    public AdminEditUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        String page;
        try {

            HttpSession session = request.getSession(true);
            Long userId = (Long) session.getAttribute(USER_ID_FOR_ADMIN);
            String userName = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String telephone = request.getParameter(TELEPHONE);
            String email = request.getParameter(EMAIL);

            User user = new User();
            user = userDao.findById(userId);

            user.setUserName(userName);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setEmail(email);

            SERVICE_USER.update(user);
            request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
            page = ADMIN_USER_EDIT;

        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = ERROR;
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page;
    }
}
