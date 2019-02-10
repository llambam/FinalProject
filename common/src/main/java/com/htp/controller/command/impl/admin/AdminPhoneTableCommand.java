package com.htp.controller.command.impl.admin;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.AdressDao;
import com.htp.dao.PhoneBookDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.Adress;
import com.htp.domain.to.PhoneBook;
import com.htp.domain.vo.PhoneBookForAdmin;
import com.htp.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

public class AdminPhoneTableCommand implements PagePath, CommandInterface {


    private static final String ACTION = "action";

    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final String BOOK_FOR_ADMIN_LIST = "bookForAdminLinkedList";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AdminPhoneTableCommand();
    }


    public AdminPhoneTableCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory();
            AdressDao adressDao = factory.getAdressDao();
            PhoneBookDao phoneBookDao = factory.getPhoneBookDao();

            List<Adress> adressList = adressDao.findAll();
            List<PhoneBook> phoneBookList = phoneBookDao.findAll();

            List<PhoneBookForAdmin> bookForAdminLinkedList = new LinkedList<>();

            for (Adress u : adressList) {

                PhoneBookForAdmin phoneBookForAdmin = new PhoneBookForAdmin();
                phoneBookForAdmin.setPhoneBookId(u.getPhoneBookID());
                phoneBookForAdmin.setUserID(u.getUserID());
                phoneBookForAdmin.setCity(u.getCity());
                phoneBookForAdmin.setDistrict(u.getDistrict());
                phoneBookForAdmin.setStreet(u.getStreet());
                phoneBookForAdmin.setHouseNumber(u.getHouseNumber());
                phoneBookForAdmin.setFloor(u.getFloor());
                phoneBookForAdmin.setApartmentNumber(u.getApartmentNumber());

                for (PhoneBook us : phoneBookList) {

                    phoneBookForAdmin.setName(us.getName());
                    phoneBookForAdmin.setSurname(us.getSurname());
                    phoneBookForAdmin.setTelephone(us.getTelephone());
                    phoneBookForAdmin.seteMail(us.geteMail());
                    phoneBookForAdmin.setCreationDate(us.getCreationDate());

                }
                bookForAdminLinkedList.add(phoneBookForAdmin);

            }

            request.setAttribute(BOOK_FOR_ADMIN_LIST, bookForAdminLinkedList);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return ADMIN_PHONES;
    }
}
