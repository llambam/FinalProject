package com.htp.service.impl;

import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.ServiceException;
import com.htp.service.UserFavoriteNumberService;

import java.util.List;

public class UserFavoriteNumberServiceImpl implements UserFavoriteNumberService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    public UserFavoriteNumberServiceImpl() {
    }

    public static UserFavoriteNumberService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final UserFavoriteNumberServiceImpl instance = new UserFavoriteNumberServiceImpl();
    }

    @Override
    public UserFavoriteNumber create(UserFavoriteNumber entity) throws ServiceException {
        return null;
    }

    @Override
    public List<UserFavoriteNumber> loadAll() throws ServiceException {
        return null;
    }
}
