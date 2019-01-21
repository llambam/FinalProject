package com.htp.dao.impl;

import com.htp.dao.UserDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;
import com.htp.dao.connection_pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao implements UserDao {


    private static final String USER_ID = "user_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TELEPHONE = "telephone";
    private static final String EMAIL = "email";
    private static final String BLOCK = "block";
    private static final String REGISRATION_DATE = "registration_date";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE user_id = ?";
    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? and password = ?";
    private static final String CREATE_USER = "INSERT INTO user (name, surname, login, password, telephone, email, registration_date) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE user SET name=?, surname=?, login=?, password=?, telephone=?, email=? WHERE user_id=? LIMIT 1";
    private static final String SELECT_ALL_ID = "SELECT user_id FROM user";


    private SQLUserDao() {
    }

    //    Demand Holder Idiom
    public static UserDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserDao instance = new SQLUserDao();
    }

    @Override
    public User getUserNode(String login, String password) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                User user = new User();
                user.setUserId(set.getLong(USER_ID));
                user.setUserName(set.getString(NAME));
                user.setSurname(set.getString(SURNAME));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setTelephone(set.getInt(TELEPHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getBoolean(BLOCK));

                return user;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean checkUser(String login, String password) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
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
    public List<User> findAll() throws DaoException {
        try (Connection connect=pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)){
            ResultSet set = statement.executeQuery();
            ArrayList<User> list = new ArrayList<>();
            while (set.next()) {
                User user= new User();
                user.setUserId(set.getLong(USER_ID));
                list.add(user);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public User findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                User user = new User();
                user.setUserId(set.getLong(USER_ID));
                user.setUserName(set.getString(NAME));
                user.setSurname(set.getString(SURNAME));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setTelephone(set.getInt(TELEPHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getBoolean(BLOCK));
                user.setRegistrationDate(set.getDate(REGISRATION_DATE));

                return user;
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
    public int create(User entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_USER)) {
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getTelephone());
            statement.setString(6, entity.getEmail());
            statement.setDate(7, (Date) entity.getRegistrationDate());
            ResultSet set = statement.executeQuery();
            return 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


    @Override
    public Long update(User entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_USER)) {
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getTelephone());
            statement.setString(6, entity.getEmail());
            statement.setLong(7, entity.getUserId());
            ResultSet set = statement.executeQuery();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


}
