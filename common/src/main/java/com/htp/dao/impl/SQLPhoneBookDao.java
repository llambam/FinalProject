package com.htp.dao.impl;

import com.htp.dao.PhoneBookDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.PhoneBook;
import com.htp.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLPhoneBookDao implements PhoneBookDao {
    private static final String PHONE_BOOK_ID = "phone_book_id";
    private static final String MAX_PHONE_BOOK_ID = "max(phone_book_id)";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String CREATION_DATE = "creation_date";

    private static final int SUBSTRING_DATE_START_INFEX=0;
    private static final int SUBSTRING_DATE_END_INFEX=10;

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SELECT_BY_ID = "SELECT * FROM phone_book WHERE phone_book_id = ?";
    private static final String SELECT_ALL_ID = "SELECT * FROM phone_book";
    private static final String DELETE_BY_ID = "DELETE FROM phone_book WHERE phone_book_id = ?";
    private static final String CREATE_PHONE_BOOK = "INSERT INTO phone_book (phone_book_id, name, surname, telephone, email, creation_date) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_PHONE_BOOK = "UPDATE phone_book SET name=?, surname=?, telephone=?, email=?, creation_date=? WHERE phone_book_id=? LIMIT 1";
    private static final String SELECT_BY_TELEPHONE = "SELECT * FROM phone_book WHERE telephone LIKE %?%";
    private static final String SELECT_BY_SURNAME = "SELECT * FROM phone_book WHERE surname LIKE %?%";
    private static final String SELECT_BY_TELEPHONE_AND_SURNAME = "SELECT * FROM phone_book WHERE surname =? telephone=?";
    private static final String SELECT_BY_TELEPHONE_UQ= "SELECT * FROM phone_book WHERE telephone = ?";
    private static final String SELECT_MAX_ID = "SELECT max(phone_book_id) FROM phone_book";

    public SQLPhoneBookDao() {
    }

    public static PhoneBookDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final PhoneBookDao instance = new SQLPhoneBookDao();
    }

    @Override
    public boolean checkUserTelephoneUQ(String telephone) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_TELEPHONE_UQ)) {
            statement.setString(1, telephone);
            ResultSet set = statement.executeQuery();
            if (set != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<PhoneBook> getPhoneBookTelephone(int telephone) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_TELEPHONE)) {
            statement.setInt(1, telephone);
            ResultSet set = statement.executeQuery();
            ArrayList<PhoneBook> list = new ArrayList<>();
            if (set.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setPhoneBookId(set.getLong(PHONE_BOOK_ID));
                phoneBook.setName(set.getString(NAME));
                phoneBook.setSurname(set.getString(SURNAME));
                phoneBook.setTelephone(set.getString(TELEPHONE));
                phoneBook.seteMail(set.getString(EMAIL));
                phoneBook.setCreationDate(Date.valueOf(set.getString(CREATION_DATE)));
                list.add(phoneBook);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<PhoneBook> getPhoneBookSurname(String surname) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_SURNAME)) {
            statement.setString(1, surname);
            ResultSet set = statement.executeQuery();
            ArrayList<PhoneBook> list = new ArrayList<>();
            while (set.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setPhoneBookId(set.getLong(PHONE_BOOK_ID));
                phoneBook.setName(set.getString(NAME));
                phoneBook.setSurname(set.getString(SURNAME));
                phoneBook.setTelephone(set.getString(TELEPHONE));
                phoneBook.seteMail(set.getString(EMAIL));
                phoneBook.setCreationDate(Date.valueOf(set.getString(CREATION_DATE)));
                list.add(phoneBook);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean checkPhoneBook(String surname, int telephone) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_TELEPHONE_AND_SURNAME)) {
            statement.setString(1, surname);
            statement.setInt(2, telephone);
            ResultSet set = statement.executeQuery();
            if (set != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PhoneBook> findAll() throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)) {
            ResultSet set = statement.executeQuery();
            ArrayList<PhoneBook> list = new ArrayList<>();
            while (set.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setPhoneBookId(set.getLong(PHONE_BOOK_ID));
                phoneBook.setName(set.getString(NAME));
                phoneBook.setSurname(set.getString(SURNAME));
                phoneBook.setTelephone(set.getString(TELEPHONE));
                phoneBook.seteMail(set.getString(EMAIL));

                String date=set.getString(CREATION_DATE).substring(SUBSTRING_DATE_START_INFEX,SUBSTRING_DATE_END_INFEX);
                phoneBook.setCreationDate(Date.valueOf(date));
                list.add(phoneBook);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public PhoneBook findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setPhoneBookId(set.getLong(PHONE_BOOK_ID));
                phoneBook.setName(set.getString(NAME));
                phoneBook.setSurname(set.getString(SURNAME));
                phoneBook.setTelephone(set.getString(TELEPHONE));
                phoneBook.seteMail(set.getString(EMAIL));
                String date=set.getString(CREATION_DATE).substring(SUBSTRING_DATE_START_INFEX,SUBSTRING_DATE_END_INFEX);
                phoneBook.setCreationDate(Date.valueOf(date));
                return phoneBook;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {

        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long create(PhoneBook entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_PHONE_BOOK)) {
            statement.setLong(1, entity.getPhoneBookId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getTelephone());
            statement.setString(5, entity.geteMail());
            statement.setString(6, String.valueOf(entity.getCreationDate()));
            statement.executeUpdate();
            PreparedStatement returnStatement = connect.prepareStatement(SELECT_MAX_ID);
            ResultSet set = returnStatement.executeQuery();
            if (set.next()) {
                Long ID = set.getLong(MAX_PHONE_BOOK_ID);
                return ID;
            }else{
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(PhoneBook entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_PHONE_BOOK)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getTelephone());
            statement.setString(4, entity.geteMail());
            statement.setString(5, String.valueOf(entity.getCreationDate()));
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
