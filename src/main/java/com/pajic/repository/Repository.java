package com.pajic.repository;

import java.util.List;

/**
 *
 * @author Cartman
 * @param <T>
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    List<T> getAllFiltered(T param, long id) throws Exception;
    List<T> findAll(T param, String searchParameter) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
}
