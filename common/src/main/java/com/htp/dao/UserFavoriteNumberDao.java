package com.htp.dao;

import com.htp.domain.to.UserFavoriteNumber;
import com.htp.exception.DaoException;

public interface UserFavoriteNumberDao extends GenericDAO<UserFavoriteNumber, Long> {

    UserFavoriteNumber foundByPhoneID(Long phone_id) throws DaoException;

}
