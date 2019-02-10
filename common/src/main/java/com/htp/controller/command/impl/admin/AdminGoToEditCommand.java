package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminGoToEditCommand implements PagePath, CommandInterface {

    private static final String USER_ID_FOR_ADMIN = "userIdFA";
    private static final String USER_ID = "userId";
    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminGoToEditCommand();
    }

    public AdminGoToEditCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Long userId = Long.valueOf(request.getParameter(USER_ID));
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID_FOR_ADMIN, userId);

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        return ADMIN_USER_EDIT;
    }
}
