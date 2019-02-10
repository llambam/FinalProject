package com.htp.dao;

import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.PhoneBook;
import com.htp.exception.DaoException;

import java.util.List;

public interface PhoneBookDao extends GenericDAO<PhoneBook, Long> {

    List<PhoneBook> getPhoneBookTelephone(int telephone) throws DaoException, ConnectionPoolException;

    List<PhoneBook> getPhoneBookSurname(String surname) throws DaoException, ConnectionPoolException;

    /**
     * Method check user node in database by login and password transfers parameters
     *
     * @param login    login unique parameter
     * @param password password parameter
     * @return boolean result of operation
     * @throws DaoException
     */
    boolean checkPhoneBook(String surname, int telephone) throws DaoException;

    boolean checkUserTelephoneUQ(String telephone) throws DaoException;
}
