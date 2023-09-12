/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pajic.repository;

/**
 *
 * @author Cartman
 * @param <T>
 */
public interface DbRepository<T>  extends Repository<T>{
    default public void connect() throws Exception{
        DbConnection.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        DbConnection.getInstance().getConnection().close();
    }
    
    default public void commit() throws Exception{
        DbConnection.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws Exception{
        DbConnection.getInstance().getConnection().rollback();
    }
}
