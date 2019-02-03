package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;

import static com.htp.domain.to.UserRolesNamesInterface.ADMIN;

public class TestMainDao {

    public static void main(String[] args) throws ParseException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }


        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();

        User user = new User();
        user.setUserId(2L);

        try {
            boolean s=factory.getUserRolesDao().findById(user.getUserId()).getRoleName().equals(ADMIN);
            System.out.println(s);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
