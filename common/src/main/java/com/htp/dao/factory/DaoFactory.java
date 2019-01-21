package com.htp.dao.factory;

import com.htp.dao.*;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }

    public abstract UserDao getUserDao();
    public abstract PhoneBookDao getPhoneBookDao();
    public abstract UserFavoriteNumberDao getUserFavoriteNumberDao();
    public abstract UserRolesDao getUserRolesDao();
    public abstract AdressDao getAdressDao();


    }