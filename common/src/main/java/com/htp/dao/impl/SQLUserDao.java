package com.htp.dao.impl;

import com.htp.dao.UserDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.User;
import com.htp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String SELECT_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String SELECT_BY_TELEPHONE= "SELECT * FROM user WHERE telephone = ?";
    private static final String CREATE_USER = "INSERT INTO user (name, surname, login, password, telephone, email, registration_date) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE user SET name=?, surname=?, login=?, password=?, telephone=?, email=?, block=? WHERE user_id=? LIMIT 1";
    private static final String SELECT_ALL_ID = "SELECT * FROM user";


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
                user.setTelephone(set.getString(TELEPHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getInt(BLOCK));

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
    public boolean checkUserloginUQ(String login) throws DaoException {
        return checkUQ(login, SELECT_BY_LOGIN);
    }

    @Override
    public boolean checkUserTelephoneUQ(String telephone) throws DaoException {
        return checkUQ(telephone, SELECT_BY_TELEPHONE);
    }

    private boolean checkUQ(String checkParametr, String sqlReqest) {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(sqlReqest)) {
            statement.setString(1, checkParametr);
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
                user.setUserName(set.getString(NAME));
                user.setSurname(set.getString(SURNAME));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setTelephone(set.getString(TELEPHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getInt(BLOCK));
                user.setRegistrationDate(set.getString(REGISRATION_DATE));
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
                user.setTelephone(set.getString(TELEPHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getInt(BLOCK));
                user.setRegistrationDate(set.getString(REGISRATION_DATE));

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
    public Long create(User entity) throws DaoException, ConnectionPoolException {
        ConnectionPool.getInstance().init();
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_USER)) {
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getTelephone());
            statement.setString(6, entity.getEmail());
            statement.setString(7, entity.getRegistrationDate());
            int rows = statement.executeUpdate();
            return entity.getUserId();
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
            statement.setString(5, entity.getTelephone());
            statement.setString(6, entity.getEmail());
            statement.setInt(7, entity.getBlocked());
            statement.setLong(8, entity.getUserId());
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


}
