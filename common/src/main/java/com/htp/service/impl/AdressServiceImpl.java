package com.htp.service.impl;

import com.htp.dao.AdressDao;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.Adress;
import com.htp.exception.DaoException;
import com.htp.exception.ServiceException;
import com.htp.service.AdressService;
import com.htp.service.validator.AdressValidator;
import com.htp.service.validator.ValidatorInterface;

public class AdressServiceImpl implements AdressService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<Adress> VALIDATE = AdressValidator.getInstance();

    public AdressServiceImpl() {
    }

    public static AdressService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final AdressServiceImpl instance = new AdressServiceImpl();
    }

    @Override
    public Long create(Adress entity) throws ServiceException {
        try {
            AdressDao adressDao = factory.getAdressDao();
            if (VALIDATE.isValid(entity)) {
                Long ID = adressDao.create(entity);
                return ID;
            } else {
                return null;
            }
        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public Adress update(Adress entity) throws ServiceException {
        try {
            AdressDao adressDao = factory.getAdressDao();
            if (VALIDATE.isValid(entity)) {
                Long id = adressDao.update(entity);
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
