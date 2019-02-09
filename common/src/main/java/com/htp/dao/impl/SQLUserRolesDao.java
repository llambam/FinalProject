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

    private static final String USER_ID = "user_id";
    private static final String MAX_USER_ID= "max(user_id)";
    private static final String USER_ROLE = "role";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM user_roles WHERE user_id = ?";
    private static final String CREATE_ROLE = "INSERT INTO user_roles (user_id, role) VALUES (?,?)";
    private static final String UPDATE_ROLE = "UPDATE user_roles SET role=? WHERE user_id=? LIMIT 1";
    private static final String SELECT_BY_ID = "SELECT * FROM user_roles WHERE user_id = ?";
    private static final String SELECT_ALL_ID = "SELECT * FROM user_roles";
    private static final String SELECT_MAX_ID = "SELECT max(user_id) FROM user_roles";

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
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)) {
            ResultSet set = statement.executeQuery();
            ArrayList<UserRoles> list = new ArrayList<>();
            while (set.next()) {
                UserRoles userRoles = new UserRoles();
                userRoles.setUserId(set.getLong(USER_ID));
                userRoles.setRoleName(set.getString(USER_ROLE));
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
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                UserRoles userRoles = new UserRoles();
                userRoles.setUserId(set.getLong(USER_ID));
                userRoles.setRoleName(set.getString(USER_ROLE));
                return userRoles;
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
    public Long create(UserRoles entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_ROLE)) {
            statement.setLong(1, entity.getUserId());
            statement.setString(2, String.valueOf(entity.getRoleName()));
            statement.executeUpdate();
            PreparedStatement returnStatement = connect.prepareStatement(SELECT_MAX_ID);
            ResultSet set = returnStatement.executeQuery();
            if (set.next()) {
                Long ID = set.getLong(MAX_USER_ID);
                return ID;
            }else{
                return null;
            }
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
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
