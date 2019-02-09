package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.exception.ServiceException;
import com.htp.service.UserService;
import com.htp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockCommand implements CommandInterface, PagePath {
    private static final UserService SERVICE = UserServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final UserDao userDao = factory.getUserDao();

    //    private static final PagesConfigManager MANAGER = PagesConfigManager.getInstance();
    private static final String USER_ID = "userId";
    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    public BlockCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new BlockCommand();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        try {
            Long userId = Long.valueOf(request.getParameter(USER_ID));
            SERVICE.block(userId);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ADMIN_USERS;

    }
}
