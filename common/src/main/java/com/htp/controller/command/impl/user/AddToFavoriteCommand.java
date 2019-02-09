package com.htp.controller.command.impl.user;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.domain.date.CurrentDate;
import com.htp.domain.date.DateInterface;
import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.ServiceException;
import com.htp.service.UserFavoriteNumberService;
import com.htp.service.impl.UserFavoriteNumberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class AddToFavoriteCommand implements PagePath, CommandInterface {

    private static final UserFavoriteNumberService SERVICE_FAVORITE_NUMBER = UserFavoriteNumberServiceImpl.getInstance();
    private static final DateInterface SERVICE_DATE = CurrentDate.getInstance();


    private static final String PHONE_BOOK_ID = "phoneBookId";
    private static final String USER_ID = "userId";

    public AddToFavoriteCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AddNewNumberCommand();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            Long phoneBookId = Long.valueOf(request.getParameter(PHONE_BOOK_ID));
            HttpSession session = request.getSession(true);
            Long userID= (Long) session.getAttribute(USER_ID);
            Date date=SERVICE_DATE.date();
            UserFavoriteNumber userFavoriteNumber = new UserFavoriteNumber(userID,phoneBookId,date);
            SERVICE_FAVORITE_NUMBER.create(userFavoriteNumber);



        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return MAIN_TABLE;
    }
}
