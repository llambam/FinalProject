package com.htp.service;


import com.htp.exception.ServiceException;

import java.util.List;

/* Basic Service interface with template parameters.
 * @param <T> generic type of objects passed to methods and can be returned
 * @param <K> generic type of objects which will serve as a view object.
 * Provides create and viewAll operations with {@link T} and {@link K} objects.
 */
public interface GenericServiceInterface<T, K> {
    /* Method adding object in database and creates the appropriate entry there
     *
     * @param entity object necessary to adding in database
     * @return {@link T} object, that method can create
     * @throws ServiceException
     */
    T create(T entity) throws ServiceException;


    T update(T entity) throws ServiceException;

    /* Method provides viewing all information and package this information in view object
     *
     * @return {@link T} object necessary for view all objects
     * @throws ServiceException
     */
//    List<T> loadAll() throws ServiceException;
}