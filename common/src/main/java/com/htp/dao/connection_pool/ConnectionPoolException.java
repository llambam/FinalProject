package com.htp.dao.connection_pool;

/**
 * The class is used to create objects of the Connection pool-level exceptions
 */
public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String definition, Exception e) {
        super(definition, e);
    }
}