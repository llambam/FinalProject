package com.htp.dao.factory;


import com.htp.dao.*;
import com.htp.dao.impl.*;

public class SQLDaoFactory extends DaoFactory {

    private static final SQLDaoFactory instance = new SQLDaoFactory();

    private SQLDaoFactory(){}

    public static SQLDaoFactory getInstance(){
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return SQLUserDao.getInstance();
    }

    @Override
    public UserFavoriteNumberDao getUserFavoriteNumberDao() {
        return SQLUserFavoriteNumberDao.getInstance();
    }

    @Override
    public PhoneBookDao getPhoneBookDao() {
        return SQLPhoneBookDao.getInstance();
    }

    @Override
    public UserRolesDao getUserRolesDao() {
        return SQLUserRolesDao.getInstance();
    }

    @Override
    public AdressDao getAdressDao() {
        return SQLAdressDao.getInstance();
    }



}