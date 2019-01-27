package com.htp.dao;

import com.htp.domain.to.Adress;
import com.htp.exception.DaoException;

public interface AdressDao extends GenericDAO<Adress, Long> {


    Adress foundByUserID(Long user_id) throws DaoException;

}
