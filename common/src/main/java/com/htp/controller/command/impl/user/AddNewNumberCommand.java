package com.htp.controller.command.impl.user;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.AdressDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.date.CurrentDate;
import com.htp.domain.date.DateInterface;
import com.htp.domain.to.Adress;
import com.htp.domain.to.PhoneBook;
import com.htp.service.AdressService;
import com.htp.service.PhoneBookService;
import com.htp.service.impl.AdressServiceImpl;
import com.htp.service.impl.PhoneBookServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@SuppressWarnings("Duplicates")
public class AddNewNumberCommand implements PagePath, CommandInterface {

    private static final AdressService SERVICE_ADRES = AdressServiceImpl.getInstance();
    private static final PhoneBookService SERVICE_PHONE_BOOK = PhoneBookServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final AdressDao adressDao= factory.getAdressDao();
    private static final DateInterface SERVICE_DATE = CurrentDate.getInstance();


    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";
    private static final String USER_ID = "userId";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String BLOCK = "block";
    private static final String REGISRATION_DATE = "date";
    private static final String CITY = "city";
    private static final String DISTRICT = "district";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";
    private static final String FLOOR = "floor";
    private static final String APARTMENT_NUMBER = "apartment_number";


    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;
    private static final Integer DEFAULT_BLOCK_VALUE = 0;


    public AddNewNumberCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new AddNewNumberCommand();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page;
        try {

            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String telephone = request.getParameter(TELEPHONE);
            String email = request.getParameter(EMAIL);


            String city = request.getParameter(CITY);
            String district = request.getParameter(DISTRICT);
            String street = request.getParameter(STREET);
            String house_number = request.getParameter(HOUSE_NUMBER);
            String floor = request.getParameter(FLOOR);
            String apartment_number = request.getParameter(APARTMENT_NUMBER);

            Adress adress = new Adress();

            HttpSession session = request.getSession(true);
            Long userID= (Long) session.getAttribute(USER_ID);

            adress.setUserID(userID);
            adress.setCity(city);
            adress.setDistrict(district);
            adress.setStreet(street);
            adress.setHouseNumber(Integer.parseInt(house_number));
            adress.setFloor(Integer.parseInt(floor));
            adress.setApartmentNumber(Integer.parseInt(apartment_number));

            Long phoneBookid = SERVICE_ADRES.create(adress);

            Date date=SERVICE_DATE.date();

            PhoneBook phoneBook = new PhoneBook();
            phoneBook.setPhoneBookId(phoneBookid);
            phoneBook.setName(name);
            phoneBook.setSurname(surname);
            phoneBook.setTelephone(telephone);
            phoneBook.seteMail(email);
            phoneBook.setCreationDate(date);

            SERVICE_PHONE_BOOK.create(phoneBook);


                page = ADD_NEW_NUMBER;
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);

        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = ERROR;
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page;
    }
}
