package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;

import java.text.ParseException;

public class TestMainDao {

    public static void main(String[] args) throws ParseException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }


        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();

        User user = new User(1L,"name","surname","login","pass","2525550","email",1,"2012-12-12");

        try {
            userDao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }


    }
}
