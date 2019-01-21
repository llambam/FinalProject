package com.htp.service.impl;

import com.htp.exception.NoSuchEntityException;
import com.htp.exception.ServiceException;
import com.htp.service.validator.LoginValidator;
import com.htp.service.validator.ValidationException;
import com.htp.service.validator.ValidatorInterface;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;
import com.htp.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<User> VALIDATE = LoginValidator.getInstance();

    private UserServiceImpl(){}

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }


    @Override
    public User loadById(Long userId) throws ServiceException, NoSuchEntityException {
        return null;
    }

    @Override
    public Long create(User entity) throws ServiceException {
        return null;
    }

    @Override
    public List<User> loadAll() throws ServiceException {
        return null;
    }

    /**
     * Method check login and password information from some user and get user object if authorization success
     * @param user object, that provides authorization operation
     * @return null if client not exists in system; user object if authorization execute correctly
     * @throws ServiceException
     */
    @Override
    public User authorization(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();

            if(VALIDATE.isValid(user)) {

                String password = user.getPassword();
                String passwordMD5 = DigestUtils.md5Hex(password);
                user.setPassword(passwordMD5);

                boolean check = userDao.checkUser(user.getLogin(), user.getPassword());
                if (!check) {
                    return null;
                } else {
                    return userDao.getUserNode(user.getLogin(), user.getPassword());
                }
            } else {
                throw new ValidationException("Validation Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }



}