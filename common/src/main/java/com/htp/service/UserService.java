package com.htp.service;

import com.htp.domain.to.User;
import com.htp.exception.ServiceException;

public interface UserService extends GenericServiceInterface <User, Long> {

    /**
     * Method provides operation for login user
     *
     * @param user object, that provides authorization operation
     * @return {@link User} - login record
     * @throws ServiceException
     */
    User authorization(User user) throws ServiceException;

    Integer block (Long userId) throws ServiceException;




}
