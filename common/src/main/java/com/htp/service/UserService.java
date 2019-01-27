package com.htp.service;

import com.htp.exception.NoSuchEntityException;
import com.htp.exception.ServiceException;
import com.htp.domain.to.User;

public interface UserService extends GenericServiceInterface <User, Long> {

    /**
     * Method provides operation for login user
     *
     * @param user object, that provides authorization operation
     * @return {@link User} - login record
     * @throws ServiceException
     */
    User authorization(User user) throws ServiceException;

    Integer block (User user) throws ServiceException;




}
