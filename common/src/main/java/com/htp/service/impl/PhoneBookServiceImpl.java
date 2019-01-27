package com.htp.service.impl;

import com.htp.dao.PhoneBookDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.PhoneBook;
import com.htp.exception.DaoException;
import com.htp.exception.ServiceException;
import com.htp.service.PhoneBookService;
import com.htp.service.validator.PhoneBookValidatir;
import com.htp.service.validator.ValidatorInterface;

import java.util.List;

public class PhoneBookServiceImpl implements PhoneBookService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<PhoneBook> VALIDATE = PhoneBookValidatir.getInstance();

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
        try {
            PhoneBookDao phoneBookDao = factory.getPhoneBookDao();
            if (VALIDATE.isValid(entity)) {
                if (phoneBookDao.checkUserTelephoneUQ(entity.getTelephone())) {
                    Long id = phoneBookDao.create(entity);
                    return entity;
                }else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PhoneBook update(PhoneBook entity) throws ServiceException {
        try {
            PhoneBookDao phoneBookDao = factory.getPhoneBookDao();
            if (VALIDATE.isValid(entity)) {
                if (phoneBookDao.checkUserTelephoneUQ(entity.getTelephone())) {
                    Long id = phoneBookDao.update(entity);
                    return entity;
                }else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PhoneBook> loadAll() throws ServiceException {
        return null;
    }
}
