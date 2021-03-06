package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.PagePath;
import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.dao.UserDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.date.CurrentDate;
import com.htp.domain.date.DateInterface;
import com.htp.domain.to.Adress;
import com.htp.domain.to.PhoneBook;
import com.htp.domain.to.User;
import com.htp.domain.to.UserRoles;
import com.htp.service.AdressService;
import com.htp.service.PhoneBookService;
import com.htp.service.UserRolesService;
import com.htp.service.UserService;
import com.htp.service.impl.AdressServiceImpl;
import com.htp.service.impl.PhoneBookServiceImpl;
import com.htp.service.impl.UserRolesServiceImpl;
import com.htp.service.impl.UserServiceImpl;
import com.htp.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

import static com.htp.domain.to.UserRolesNamesInterface.USER;

/* Class is designed for the registration of a new client
 */
public class RegistrationCommand implements CommandInterface, PagePath {
    private static final UserService SERVICE_USER = UserServiceImpl.getInstance();
    private static final UserRolesService SERVICE_USER_ROLES = UserRolesServiceImpl.getInstance();
    private static final AdressService SERVICE_ADRES = AdressServiceImpl.getInstance();
    private static final PhoneBookService SERVICE_PHONE_BOOK = PhoneBookServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final UserDao userDao = factory.getUserDao();
    private static final DateInterface SERVICE_DATE = CurrentDate.getInstance();


    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";

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

    private RegistrationCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new RegistrationCommand();
    }

    /* Method performs the procedure for adding a new customer to the system.
     * Getting all information about new client and then create new node in system.
     * Also determines what action must be made for transition(forward or sendRedirect).
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException when creating fail
     */


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String page;
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String telephone = request.getParameter(TELEPHONE);
            String email = request.getParameter(EMAIL);
            Integer isBlocked = DEFAULT_BLOCK_VALUE;


            String city = request.getParameter(CITY);
            String district = request.getParameter(DISTRICT);
            String street = request.getParameter(STREET);
            String house_number = request.getParameter(HOUSE_NUMBER);
            String floor = request.getParameter(FLOOR);
            String apartment_number = request.getParameter(APARTMENT_NUMBER);

            Date date = SERVICE_DATE.date();

            User user = new User();
            user.setUserName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setEmail(email);
            user.setBlocked(isBlocked);
            user.setRegistrationDate(date);

            Long resultClient = SERVICE_USER.create(user);

            User userWithID = userDao.getUserNode(user.getLogin(), user.getPassword());
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(userWithID.getUserId());
            userRoles.setRoleName(USER);
            SERVICE_USER_ROLES.create(userRoles);

            Adress adress = new Adress();

            adress.setUserID(userWithID.getUserId());
            adress.setCity(city);
            adress.setDistrict(district);
            adress.setStreet(street);
            adress.setHouseNumber(Integer.parseInt(house_number));
            adress.setFloor(Integer.parseInt(floor));
            adress.setApartmentNumber(Integer.parseInt(apartment_number));

            Long phoneBookId = SERVICE_ADRES.create(adress);

            PhoneBook phoneBook = new PhoneBook();
            phoneBook.setPhoneBookId(phoneBookId);
            phoneBook.setName(user.getUserName());
            phoneBook.setSurname(user.getSurname());
            phoneBook.setTelephone(user.getTelephone());
            phoneBook.seteMail(user.getEmail());
            phoneBook.setCreationDate(user.getRegistrationDate());

            SERVICE_PHONE_BOOK.create(phoneBook);

            if (resultClient == null) {
                request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE_2);
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
                page = REGISTRATION;
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute(CLIENT_ROLE, resultClient);
                page = INDEX;
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
            }
        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = REGISTRATION;
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page;
    }
}