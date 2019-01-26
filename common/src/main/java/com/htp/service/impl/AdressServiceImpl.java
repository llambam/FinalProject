package com.htp.service.impl;

import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.Adress;
import com.htp.exception.ServiceException;
import com.htp.service.AdressService;

import java.util.List;

public class AdressServiceImpl implements AdressService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    public AdressServiceImpl() {
    }

    public static AdressService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final AdressServiceImpl instance = new AdressServiceImpl();
    }

    @Override
    public Adress create(Adress entity) throws ServiceException {
        return null;
    }

    @Override
    public List<Adress> loadAll() throws ServiceException {
        return null;
    }
}
