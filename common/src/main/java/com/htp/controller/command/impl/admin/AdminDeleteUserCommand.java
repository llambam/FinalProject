package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.factory.DaoFactory;
import com.htp.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminDeleteUserCommand implements PagePath, CommandInterface {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final String ACTION = "action";

    private static final String USER_ID_FOR_ADMIN = "userIdFA";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final String USER_FOR_ADMIN_TABLE_LIST = "userForAdminTableList";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminDeleteUserCommand();
    }

    public AdminDeleteUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(true);
            Long userId = (Long) session.getAttribute(USER_ID_FOR_ADMIN);
            factory.getUserRolesDao().delete(userId);
            factory.getUserDao().delete(userId);

            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return ADMIN_USERS;
    }
}
