package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.domain.to.UserRoles;
import com.htp.service.UserRolesService;
import com.htp.service.UserService;
import com.htp.service.impl.UserRolesServiceImpl;
import com.htp.service.impl.UserServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.htp.domain.to.UserRolesNamesInterface.USER;

/* Class is designed for the registration of a new client
 */
public class RegistrationCommand implements CommandInterface, PagePath {
    private static final UserService SERVICE_USER = UserServiceImpl.getInstance();
    private static final UserRolesService SERVICE_USER_ROLES = UserRolesServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
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

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String page;
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String telephone = request.getParameter(TELEPHONE);
            String email = request.getParameter(EMAIL);
            Integer isBlocked = DEFAULT_BLOCK_VALUE;
            String registrationDate = request.getParameter(REGISRATION_DATE);


            User user = new User();
            user.setUserName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setEmail(email);
            user.setBlocked(isBlocked);
            user.setRegistrationDate(registrationDate);

            User resultClient = SERVICE_USER.create(user);

            User userWithID = userDao.getUserNode(user.getLogin(), user.getPassword());
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(userWithID.getUserId());
            userRoles.setRoleName(USER);
            SERVICE_USER_ROLES.create(userRoles);


            if (resultClient == null) {
                request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE_2);
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
                page = REGISTRATION;
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute(CLIENT_ROLE, resultClient);
                page = ADRESS;
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
            }
        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = REGISTRATION;
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page;
    }
}