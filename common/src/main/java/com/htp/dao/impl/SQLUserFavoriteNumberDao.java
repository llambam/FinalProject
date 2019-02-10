package com.htp.dao.impl;

import com.htp.dao.UserFavoriteNumberDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserFavoriteNumberDao implements UserFavoriteNumberDao {

    private static final String USER_ID = "user_id";
    private static final String MAX_USER_ID = "max(user_id)";
    private static final String PHONE_BOOK_ID = "phone_book_id";
    private static final String DATE = "date";

    private static final int SUBSTRING_DATE_START_INFEX = 0;
    private static final int SUBSTRING_DATE_END_INFEX = 10;

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM user_favorite_number WHERE user_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM user_favorite_number WHERE user_id = ?";
    private static final String SELECT_BY_PHONE_ID = "SELECT * FROM user_favorite_number WHERE phone_book_id = ?";
    private static final String CREATE_FAVORITE_NUMBER = "INSERT INTO user_favorite_number (phone_book_id, date) VALUES (?,?)";
    private static final String UPDATE_USER_FAV_NUMBER = "UPDATE user_favorite_number SET phone_book_id=?, date=?  WHERE user_id=? LIMIT 1";
    private static final String SELECT_ALL_ID = "SELECT * FROM user_favorite_number";
    private static final String SELECT_MAX_ID = "SELECT max(user_id) FROM user_favorite_number";

    public SQLUserFavoriteNumberDao() {
    }

    //    Demand Holder Idiom
    public static UserFavoriteNumberDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserFavoriteNumberDao instance = new SQLUserFavoriteNumberDao();
    }

    @Override
    public UserFavoriteNumber foundByPhoneID(Long phone_id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_PHONE_ID)) {
            statement.setLong(1, phone_id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                UserFavoriteNumber userFavoriteNumber = new UserFavoriteNumber();
                userFavoriteNumber.setUserID(set.getLong(USER_ID));
                userFavoriteNumber.setPhoneBookID(set.getLong(PHONE_BOOK_ID));
                userFavoriteNumber.setDate(Date.valueOf(set.getString(DATE)));
                return userFavoriteNumber;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<UserFavoriteNumber> findAll() throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)) {
            ResultSet set = statement.executeQuery();
            List<UserFavoriteNumber> list = new ArrayList<>();
            while (set.next()) {
                UserFavoriteNumber userFavoriteNumber = new UserFavoriteNumber();
                userFavoriteNumber.setUserID(set.getLong(USER_ID));
                userFavoriteNumber.setPhoneBookID(set.getLong(PHONE_BOOK_ID));

                String date = set.getString(DATE).substring(SUBSTRING_DATE_START_INFEX, SUBSTRING_DATE_END_INFEX);
                userFavoriteNumber.setDate(Date.valueOf(date));
                list.add(userFavoriteNumber);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }

    @Override
    public UserFavoriteNumber findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                UserFavoriteNumber userFavoriteNumber = new UserFavoriteNumber();
                userFavoriteNumber.setUserID(set.getLong(USER_ID));
                userFavoriteNumber.setPhoneBookID(set.getLong(PHONE_BOOK_ID));
                String date = set.getString(DATE).substring(SUBSTRING_DATE_START_INFEX, SUBSTRING_DATE_END_INFEX);
                userFavoriteNumber.setDate(Date.valueOf(date));
                return userFavoriteNumber;
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
            throw new DaoException("Exception! ", e);
        }
    }

    @Override
    public Long create(UserFavoriteNumber entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_FAVORITE_NUMBER)) {
            statement.setLong(1, entity.getPhoneBookID());
            statement.setString(2, String.valueOf(entity.getDate()));
            PreparedStatement returnStatement = connect.prepareStatement(SELECT_MAX_ID);
            statement.executeUpdate();
            ResultSet set = returnStatement.executeQuery();
            if (set.next()) {
                Long ID = set.getLong(MAX_USER_ID);
                return ID;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(UserFavoriteNumber entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_USER_FAV_NUMBER)) {
            statement.setLong(1, entity.getPhoneBookID());
            statement.setString(2, String.valueOf(entity.getDate()));
            statement.setLong(3, entity.getUserID());
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


}

