package com.htp.controller.command.impl.user;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.PhoneBookDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.PhoneBook;
import com.htp.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PhoneBookTableCommand implements CommandInterface, PagePath {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    public PhoneBookTableCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new PhoneBookTableCommand();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        try {
            DaoFactory factory = DaoFactory.getDaoFactory();
            PhoneBookDao phoneBookDao = factory.getPhoneBookDao();
//            AdressDao adressDao=factory.getAdressDao();

            List<PhoneBook> phoneBookList = phoneBookDao.findAll();
//            List<Adress> adressList=adressDao.findAll();
            request.setAttribute("phoneBookList", phoneBookList);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return MAIN_TABLE;
    }
}
