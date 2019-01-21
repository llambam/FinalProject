package com.htp.dao.impl;

import com.htp.dao.UserRolesDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.UserRoles;
import com.htp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserRolesDao implements UserRolesDao {

    private static final String USER_ID="user_id";
    private static final String USER_ROLE="role";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM user_roles WHERE user_id = ?";
    private static final String CREATE_ROLE = "INSERT INTO user_roles (role) VALUES (?)";
    private static final String UPDATE_ROLE = "UPDATE user_roles SET role=? WHERE user_id=? LIMIT 1";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    private static final String SELECT_ALL_ID = "SELECT user_id FROM user_roles";

    public SQLUserRolesDao() {
    }

    //    Demand Holder Idiom
    public static UserRolesDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserRolesDao instance = new SQLUserRolesDao();
    }

    @Override
    public List<UserRoles> findAll() throws DaoException {
        try (Connection connect=pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)){
            ResultSet set = statement.executeQuery();
            ArrayList<UserRoles> list = new ArrayList<>();
            while (set.next()) {
                UserRoles userRoles= new UserRoles();
                userRoles.setUserId(set.getLong(USER_ID));
                list.add(userRoles);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserRoles findById(Long id) throws DaoException {
//        try (Connection connect = pool.getConnection();
//             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
//            statement.setLong(1, id);
//            ResultSet set = statement.executeQuery();
//
//            if (set.next()) {
//                UserRoles userRoles = new UserRoles();
//                userRoles.setUserId(set.getLong(USER_ID));
//                userRoles.setRoleName(set.getString(UPDATE_ROLE));
//
//                return userRoles;
//            } else {
//                return null;
//            }
//        } catch (SQLException | ConnectionPoolException e) {
//            throw new DaoException("Exception", e);
//        }

        return null;//!!!!!!!!!!!!!!!
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
    public int create(UserRoles entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_ROLE)) {
            statement.setString(1, String.valueOf(entity.getRoleName()));
            ResultSet set = statement.executeQuery();
            return 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(UserRoles entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_ROLE)) {
            statement.setString(1, String.valueOf(entity.getRoleName()));
            statement.setLong(2, entity.getUserId());
            ResultSet set = statement.executeQuery();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}