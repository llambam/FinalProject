package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.UserRolesDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.domain.to.UserRoles;
import com.htp.domain.vo.UserForAdminTable;
import com.htp.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminShowUserCommand implements PagePath, CommandInterface {

    private static final String ACTION = "action";
    private static final String USER_ID_FOR_ADMIN = "userIdFA";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final String ADMIN_TABLE_ = "userForAdminTable";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminShowUserCommand();
    }

    public AdminShowUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory();
            UserDao userDao = factory.getUserDao();
            UserRolesDao userRolesDao = factory.getUserRolesDao();

            HttpSession session = request.getSession(true);
            Long userId = (Long) session.getAttribute(USER_ID_FOR_ADMIN);
            User user = new User();
            user = userDao.findById(userId);

            UserRoles userRoles = new UserRoles();
            userRoles = userRolesDao.findById(userId);


            UserForAdminTable userForAdminTable = new UserForAdminTable();
            userForAdminTable.setUserId(user.getUserId());
            userForAdminTable.setUserName(user.getUserName());
            userForAdminTable.setSurname(user.getSurname());
            userForAdminTable.setLogin(user.getLogin());
            userForAdminTable.setPassword(user.getPassword());
            userForAdminTable.setTelephone(user.getTelephone());
            userForAdminTable.setEmail(user.getEmail());
            userForAdminTable.setBlocked(user.getBlocked());
            userForAdminTable.setRegistrationDate(user.getRegistrationDate());
            userForAdminTable.setRoleName(userRoles.getRoleName());

            request.setAttribute(ADMIN_TABLE_, userForAdminTable);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return ADMIN_USER_EDIT;
    }
}
