package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;
import com.htp.exception.ServiceException;
import com.htp.service.UserService;
import com.htp.service.impl.UserServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.htp.domain.to.UserRolesNamesInterface.ADMIN;
import static com.htp.domain.to.UserRolesNamesInterface.USER;


/* Class is designed for the log in in system as administrator or client
 */
public class LoginCommand implements CommandInterface, PagePath {

    private static final UserService SERVICE = UserServiceImpl.getInstance();
    //    private static final PagesConfigManager MANAGER = PagesConfigManager.getInstance();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ADMIN_ROLE = "admin";
    private static final String USER_ID = "userId";
    private static final String CLIENT_ROLE = "client";
    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private LoginCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LoginCommand();
    }

    /* Method performs the procedure for authorization in system
     * In first, getting login and password parameters from request
     * Then finding node with equals parameters. If procedure return not null, then necessary define client or admin
     * log in. According to role of user creating admin or client object and put into session.
     *
     * Also determines what action must be made for transition(forward or sendRedirect)
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException if authorization method process fail
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String page = null;
        try {
            User tempUser = new User();
            tempUser.setLogin(request.getParameter(LOGIN));
            String password = request.getParameter(PASSWORD);
            tempUser.setPassword(password);

            HttpSession session = request.getSession(true);
            User user = SERVICE.authorization(tempUser);
            DaoFactory factory = DaoFactory.getDaoFactory();

            Long sessionUserID = user.getUserId();
            session.setAttribute(USER_ID, sessionUserID);
            User userforBlockCheck = new User();
            userforBlockCheck = factory.getUserDao().findById(sessionUserID);


            if (user == null) {
                request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
                request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);//  одна из возможных реализаций отображений эррор мэссэджа у клиента
                page = INDEX;
            } else {
                if (userforBlockCheck.getBlocked() == 0) {
                    if (factory.getUserRolesDao().findById(user.getUserId()).getRoleName().equals(ADMIN)) {
                        session.setAttribute(ADMIN_ROLE, user);
                        page = ADMIN_USERS;
                    }
                    if (factory.getUserRolesDao().findById(user.getUserId()).getRoleName().equals(USER)) {
                        session.setAttribute(CLIENT_ROLE, user);
                        page = MAIN_TABLE;
                    }
                } else {
                    page = BLOCKED;
                }
            }
            request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);

        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = INDEX;
        } catch (ServiceException e) {
            throw new CommandException("Command Exception", e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return page;
    }
}