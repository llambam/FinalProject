package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.dao.impl.SQLUserDao;
import com.htp.dao.impl.SQLUserRolesDao;
import com.htp.domain.enums.Role;
import com.htp.domain.to.Adress;
import com.htp.domain.to.User;
import com.htp.domain.to.UserRoles;
import com.htp.exception.DaoException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {

        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        UserRoles userRoles = new UserRoles(2L, Role.ADMIN);
        Adress adress = new Adress(1l, 1l, "asda", "asdd", "sss", 2, 5, 3);

        DaoFactory factory = DaoFactory.getDaoFactory();
        UserRolesDao userRolesDao = factory.getUserRolesDao();
        AdressDao adressDao = factory.getAdressDao();

        try {
//            userRolesDao.create(userRoles);
            adressDao.create(adress);
            List<Adress> list =adressDao.findAll();

            System.out.println(list);
//            System.out.println(userRolesDao.findById(2L));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }
}
