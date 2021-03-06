package com.htp.service.impl;

import com.htp.dao.UserRolesDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.UserRoles;
import com.htp.exception.DaoException;
import com.htp.exception.ServiceException;
import com.htp.service.UserRolesService;
import com.htp.service.validator.UserRolesValidator;
import com.htp.service.validator.ValidatorInterface;

public class UserRolesServiceImpl implements UserRolesService {


    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<UserRoles> VALIDATE = UserRolesValidator.getInstance();

    public UserRolesServiceImpl() {
    }

    public static UserRolesService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final UserRolesServiceImpl instance = new UserRolesServiceImpl();
    }


    @Override
    public Long create(UserRoles entity) throws ServiceException {
        try {
            UserRolesDao userRolesDao = factory.getUserRolesDao();
            if (VALIDATE.isValid(entity)) {
                Long id = userRolesDao.create(entity);
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
    public UserRoles update(UserRoles entity) throws ServiceException {
        try {
            UserRolesDao userRolesDao = factory.getUserRolesDao();
            if (VALIDATE.isValid(entity)) {
                Long id = userRolesDao.update(entity);
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
