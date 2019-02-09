package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements PagePath, CommandInterface {


    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";


    public LogoutCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LogoutCommand();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);

        return INDEX;
    }
}
