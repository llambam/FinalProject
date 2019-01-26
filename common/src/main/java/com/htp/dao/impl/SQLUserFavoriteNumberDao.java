package com.htp.dao.impl;

import com.htp.dao.UserFavoriteNumberDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserFavoriteNumberDao implements UserFavoriteNumberDao {

    private static final String USER_ID="user_id";
    private static final String PHONE_BOOK_ID="phone_book_id";
    private static final String DATE="date";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM user_favorite_number WHERE user_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM user_favorite_number WHERE user_id = ?";
    private static final String SELECT_BY_PHONE_ID = "SELECT * FROM user_favorite_number WHERE phone_book_id = ?";
    private static final String CREATE_FAVORITE_NUMBER = "INSERT INTO user_favorite_number (phone_book_id, date) VALUES (?,?)";
    private static final String UPDATE_USER_FAV_NUMBER = "UPDATE user_favorite_number SET phone_book_id=?, date=?  WHERE user_id=? LIMIT 1";
    private static final String SELECT_ALL_ID = "SELECT user_id FROM user_favorite_number";

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
                userFavoriteNumber.setUserID(set.getLong( USER_ID));
                userFavoriteNumber.setPhoneBookID(set.getLong(PHONE_BOOK_ID));
                userFavoriteNumber.setDate(set.getString(DATE));
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
        try (Connection connect=pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)){
            ResultSet set = statement.executeQuery();
            ArrayList<UserFavoriteNumber> list = new ArrayList<>();
            while (set.next()) {
                UserFavoriteNumber userFavoriteNumber= new UserFavoriteNumber();
                userFavoriteNumber.setUserID(set.getLong(USER_ID));
                list.add(userFavoriteNumber);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return null;
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
                userFavoriteNumber.setUserID(set.getLong( USER_ID));
                userFavoriteNumber.setPhoneBookID(set.getLong(PHONE_BOOK_ID));
                userFavoriteNumber.setDate(set.getString(DATE));
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
            return false;
        }
    }

    @Override
    public Long create(UserFavoriteNumber entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_FAVORITE_NUMBER)) {
            statement.setLong(1, entity.getPhoneBookID());
            statement.setString(2, String.valueOf(entity.getDate()));
            ResultSet set = statement.executeQuery();
            return entity.getPhoneBookID();
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
            ResultSet set = statement.executeQuery();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


}

