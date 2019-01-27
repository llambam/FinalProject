package com.htp.service.impl;

import com.htp.exception.NoSuchEntityException;
import com.htp.exception.ServiceException;
import com.htp.service.validator.LoginValidator;
import com.htp.service.validator.UserValidator;
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
    private static final ValidatorInterface<User> VALIDATE_LOGIN = LoginValidator.getInstance();
    private static final ValidatorInterface<User> VALIDATE_USER = UserValidator.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }


    @Override
    public User create(User entity) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            if (VALIDATE_USER.isValid(entity) & VALIDATE_LOGIN.isValid(entity)) {
                boolean check = UserPasswordHash(entity, userDao);
                if (!check & userDao.checkUserloginUQ(entity.getLogin()) & userDao.checkUserTelephoneUQ(entity.getTelephone())) {
                    Long id = userDao.create(entity);
                    return entity;
                } else {
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
    public User update(User entity) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            if (VALIDATE_USER.isValid(entity) & VALIDATE_LOGIN.isValid(entity)) {
                boolean check = UserPasswordHash(entity, userDao);
                if (!check & userDao.checkUserloginUQ(entity.getLogin()) & userDao.checkUserTelephoneUQ(entity.getTelephone())) {
                    Long id = userDao.update(entity);
                    return entity;
                } else {
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
    public List<User> loadAll() throws ServiceException {
        return null;
    }

    /**
     * Method check login and password information from some user and get user object if authorization success
     *
     * @param user object, that provides authorization operation
     * @return null if client not exists in system; user object if authorization execute correctly
     * @throws ServiceException
     */
    @Override
    public User authorization(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();

            if (VALIDATE_LOGIN.isValid(user)) {
                boolean check = UserPasswordHash(user, userDao);
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

    @Override
    public Integer block(User user) throws ServiceException {
       try {
           UserDao userDao = factory.getUserDao();

           int block = user.getBlocked();
           if (block == 0) {
               user.setBlocked(1);
               userDao.update(user);
           return 1;//возвращает новое(текущее) значение
           }
           if(block==1){
               user.setBlocked(0);
               userDao.update(user);
               return 0;
           }else {
               return null;
           }
       }catch (DaoException e){
           throw new ServiceException("Service Exception", e);
       }
    }


    private boolean UserPasswordHash(User entity, UserDao userDao) throws DaoException {
        String password = entity.getPassword();
        String passwordMD5 = DigestUtils.md5Hex(password);
        entity.setPassword(passwordMD5);
        boolean check;
        if (userDao.checkUser(entity.getLogin(), entity.getPassword())) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}