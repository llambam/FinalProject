package com.htp.service.impl;

import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.PhoneBook;
import com.htp.exception.ServiceException;
import com.htp.service.PhoneBookService;

import java.util.List;

public class PhoneBookServiceImpl implements PhoneBookService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    public PhoneBookServiceImpl() {
    }

    public static PhoneBookService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final PhoneBookServiceImpl instance = new PhoneBookServiceImpl();
    }

    @Override
    public PhoneBook create(PhoneBook entity) throws ServiceException {
        return null;
    }

    @Override
    public List<PhoneBook> loadAll() throws ServiceException {
        return null;
    }
}
