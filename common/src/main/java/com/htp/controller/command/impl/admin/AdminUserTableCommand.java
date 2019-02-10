package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.UserRolesDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.domain.to.UserRoles;
import com.htp.domain.vo.UserForAdminTable;
import com.htp.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

public class AdminUserTableCommand implements PagePath, CommandInterface {


    private static final String ACTION = "action";

    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final String USER_FOR_ADMIN_TABLE_LIST = "userForAdminTableList";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminUserTableCommand();
    }


    public AdminUserTableCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory();
            UserDao userDao = factory.getUserDao();
            UserRolesDao userRolesDao = factory.getUserRolesDao();

            List<User> userList = userDao.findAll();
            List<UserRoles> userRoles = userRolesDao.findAll();

            List<UserForAdminTable> userForAdminTableList = new LinkedList<>();

            for (User u : userList) {


                UserForAdminTable userForAdminTable = new UserForAdminTable();
                userForAdminTable.setUserId(u.getUserId());
                userForAdminTable.setUserName(u.getUserName());
                userForAdminTable.setSurname(u.getSurname());
                userForAdminTable.setLogin(u.getLogin());
                userForAdminTable.setTelephone(u.getTelephone());
                userForAdminTable.setEmail(u.getEmail());
                userForAdminTable.setBlocked(u.getBlocked());
                userForAdminTable.setRegistrationDate(u.getRegistrationDate());

                for (UserRoles us : userRoles) {
                    if (us.getUserId().equals(u.getUserId())) {
                        userForAdminTable.setRoleName(us.getRoleName());
                    }
                }
                userForAdminTableList.add(userForAdminTable);

            }

            request.setAttribute(USER_FOR_ADMIN_TABLE_LIST, userForAdminTableList);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return ADMIN_USERS;
    }
}
