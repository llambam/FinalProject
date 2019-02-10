package com.htp.service.impl;

import com.htp.dao.UserFavoriteNumberDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.DaoException;
import com.htp.exception.ServiceException;
import com.htp.service.UserFavoriteNumberService;
import com.htp.service.validator.UserFavoriteNumberValidatir;
import com.htp.service.validator.ValidatorInterface;

public class UserFavoriteNumberServiceImpl implements UserFavoriteNumberService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    public UserFavoriteNumberServiceImpl() {
    }

    public static UserFavoriteNumberService getInstance() {
        return SingletonHolder.instance;
    }

    private static final ValidatorInterface<UserFavoriteNumber> VALIDATE = UserFavoriteNumberValidatir.getInstance();

    private static class SingletonHolder {

        private static final UserFavoriteNumberServiceImpl instance = new UserFavoriteNumberServiceImpl();
    }

    @Override
    public Long create(UserFavoriteNumber entity) throws ServiceException {
        try {
            UserFavoriteNumberDao userFavoriteNumberDao = factory.getUserFavoriteNumberDao();
            if (VALIDATE.isValid(entity)) {
                Long id = userFavoriteNumberDao.create(entity);
                return id;
            } else {
                return null;
            }
        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public UserFavoriteNumber update(UserFavoriteNumber entity) throws ServiceException {
        try {
            UserFavoriteNumberDao userFavoriteNumberDao = factory.getUserFavoriteNumberDao();
            if (VALIDATE.isValid(entity)) {
                Long id = userFavoriteNumberDao.update(entity);
                return entity;
            } else {
                return null;
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Service Exception", e);
        }
    }
}
