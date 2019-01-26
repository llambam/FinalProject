package com.htp.service.impl;

import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.UserRoles;
import com.htp.exception.ServiceException;
import com.htp.service.UserRolesService;

import java.util.List;

public class UserRolesServiceImpl implements UserRolesService {


    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    public UserRolesServiceImpl() {
    }

    public static UserRolesService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final UserRolesServiceImpl instance = new UserRolesServiceImpl();
    }


    @Override
    public UserRoles create(UserRoles entity) throws ServiceException {
        return null;
    }

    @Override
    public List<UserRoles> loadAll() throws ServiceException {
        return null;
    }
}
