package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminGoToEditPhonesCommand implements PagePath, CommandInterface {


    private static final String PHONE_BOOK_ID_FOR_ADMIN = "phoneBookIdFA";
    private static final String PHONE_BOOK_ID = "phoneBookId";
    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminGoToEditPhonesCommand();
    }


    public AdminGoToEditPhonesCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Long phoneBookId = Long.valueOf(request.getParameter(PHONE_BOOK_ID));
        HttpSession session = request.getSession(true);
        session.setAttribute(PHONE_BOOK_ID_FOR_ADMIN, phoneBookId);

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        return ADMIN_PHONES_EDIT;
    }
}
