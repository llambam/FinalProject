package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestMainDao {

    public static void main(String[] args) throws ParseException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String regexDate = "yyyy-MM-dd";
        String realRegexDate = "^\\d{4}-\\d{2}-\\d{2}$";
        String s = "2019-02-09_ 00:00:00.000000";

        s = s.substring(0, 10);
        System.out.println(s);

        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();


        DateFormat dateFormat = new SimpleDateFormat(regexDate);
        Date date = new Date(System.currentTimeMillis());
        System.out.println(dateFormat.format(date));

//        User user = new User(5L, "ss", "ss", "ss", "ss", "ss", "ss", 0, Date.valueOf(dateFormat.format(date)));
//
//        try {
//            userDao.create(user);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        } catch (ConnectionPoolException e) {
//            e.printStackTrace();
//        }


    }
}
