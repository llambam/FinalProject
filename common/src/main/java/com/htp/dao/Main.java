package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.exception.DaoException;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;

public class Main {

    public static void main(String[] args) {
        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();
        try {
            userDao.findById(1L);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
