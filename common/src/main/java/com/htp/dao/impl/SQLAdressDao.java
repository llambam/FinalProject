package com.htp.dao.impl;

import com.htp.dao.AdressDao;
import com.htp.dao.connection_pool.ConnectionPool;
import com.htp.dao.connection_pool.ConnectionPoolException;
import com.htp.domain.to.Adress;
import com.htp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class SQLAdressDao implements AdressDao {

    private static final Long ADMIN_ID = 2L;

    private static final String PHONE_BOOK_ID = "phone_book_id";
    private static final String MAX_PHONE_BOOK_ID = "max(phone_book_id)";
    private static final String USER_ID = "user_id";
    private static final String CITY = "city";
    private static final String DISTRICT = "district";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";
    private static final String FLOOR = "floor";
    private static final String APARTMENT_NUMBER = "apartment_number";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM adress WHERE phone_book_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM adress WHERE phone_book_id = ?";
    private static final String SELECT_ALL_ID = "SELECT * FROM adress";
    private static final String SELECT_ID_BY_ALL = "SELECT phone_book_id FROM adress WHERE  city=?, district=?, street=?, house_number=?, floor=?, apartment_number=?";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM adress WHERE user_id = ?";
    private static final String CREATE_ADRESS = "INSERT INTO adress (user_id, city, district, street, house_number, floor, apartment_number) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_ADRESS = "UPDATE adress SET user_id=?, city=?, district=?, street=?, house_number=?, floor=?, apartment_number=?  WHERE phone_book_id=? LIMIT 1";
    private static final String SELECT_MAX_ID = "SELECT max(phone_book_id) FROM adress";


    public SQLAdressDao() {
    }

    public static AdressDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final AdressDao instance = new SQLAdressDao();
    }


    @Override
    public Adress foundByUserID(Long user_id) throws DaoException {
        return getAdress(user_id, SELECT_BY_USER_ID);
    }


    @Override
    public Long getAdressId(Adress adress) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ID_BY_ALL)) {
            statement.setString(1, adress.getCity());
            statement.setString(2, adress.getDistrict());
            statement.setString(3, adress.getStreet());
            statement.setInt(4, adress.getHouseNumber());
            statement.setInt(5, adress.getFloor());
            statement.setInt(6, adress.getApartmentNumber());
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                Long ID = null;
                ID = set.getLong(PHONE_BOOK_ID);
                return ID;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


    @Override
    public List<Adress> findAll() throws DaoException, ConnectionPoolException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)) {
            ResultSet set = statement.executeQuery();
            ArrayList<Adress> list = new ArrayList<>();
            while (set.next()) {
                list.add(fillingAdress(set));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }

    @Override
    public Adress findById(Long id) throws DaoException {
        return getAdress(id, SELECT_BY_ID);
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }

    @Override
    public Long create(Adress entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_ADRESS)) {
            if (entity.getUserID() == null) {
                entity.setUserID(ADMIN_ID);
            }
            statement.setLong(1, entity.getUserID());
            statement.setString(2, entity.getCity());
            statement.setString(3, entity.getDistrict());
            statement.setString(4, entity.getStreet());
            statement.setInt(5, entity.getHouseNumber());
            statement.setInt(6, entity.getFloor());
            statement.setInt(7, entity.getApartmentNumber());
            statement.executeUpdate();

            PreparedStatement returnStatement = connect.prepareStatement(SELECT_MAX_ID);
            ResultSet set = returnStatement.executeQuery();
            if (set.next()) {
                Long ID = set.getLong(MAX_PHONE_BOOK_ID);
                return ID;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(Adress entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_ADRESS)) {
            statement.setLong(1, entity.getUserID());
            statement.setString(2, entity.getCity());
            statement.setString(3, entity.getDistrict());
            statement.setString(4, entity.getStreet());
            statement.setLong(5, entity.getHouseNumber());
            statement.setLong(6, entity.getFloor());
            statement.setLong(7, entity.getApartmentNumber());
            statement.setLong(8, entity.getPhoneBookID());
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    private Adress fillingAdress(ResultSet set) throws SQLException {
        Adress adress = new Adress();
        adress.setPhoneBookID(set.getLong(PHONE_BOOK_ID));
        adress.setUserID(set.getLong(USER_ID));
        adress.setCity(set.getString(CITY));
        adress.setDistrict(set.getString(DISTRICT));
        adress.setStreet(set.getString(STREET));
        adress.setHouseNumber(set.getInt(HOUSE_NUMBER));
        adress.setFloor(set.getInt(FLOOR));
        adress.setApartmentNumber(set.getInt(APARTMENT_NUMBER));
        return adress;
    }

    private Adress getAdress(Long user_id, String selectByUserId) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(selectByUserId)) {
            statement.setLong(1, user_id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return fillingAdress(set);
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
