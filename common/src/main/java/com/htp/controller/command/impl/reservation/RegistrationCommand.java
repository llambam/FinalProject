package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.domain.to.User;
import com.htp.service.UserService;
import com.htp.service.impl.UserServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* Class is designed for the registration of a new client
 */
public class RegistrationCommand implements CommandInterface {
    private static final UserService SERVICE = UserServiceImpl.getInstance();
//    private static final PagesConfigManager MANAGER = PagesConfigManager.getInstance();

    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String BLOCK = "block";
    private static final String REGISRATION_DATE = "registration_date";
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;

    private RegistrationCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new RegistrationCommand();
    }

    /* Method performs the procedure for adding a new customer to the system.
     * Getting all information about new client and then create new node in system.
     * Also determines what action must be made for transition(forward or sendRedirect).
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException when creating fail
     */


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page;
        try {
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String telephone = request.getParameter(TELEPHONE);
            String email = request.getParameter(EMAIL);
            int isBlocked = Integer.parseInt(request.getParameter(BLOCK));
            String registrationDate = request.getParameter(REGISRATION_DATE);

            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);

            User user = new User();
            user.setUserName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setEmail(email);
            user.setBlocked(isBlocked);
            user.setRegistrationDate(registrationDate);

            User resultClient = SERVICE.create(user);
            if (resultClient == null) {
                request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE_2);
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
//                page = MANAGER.getProperty(PagePath.REGISTRATION.toString());
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute(CLIENT_ROLE, resultClient);
//                page = MANAGER.getProperty(PagePath.RESULT.toString());
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
            }
        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
//            page = MANAGER.getProperty(PagePath.REGISTRATION.toString());
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
//        return page;
        return null;
    }
}