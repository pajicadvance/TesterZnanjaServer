/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pajic.repository;

import com.pajic.model.GenericEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja genericku implementaciju repozitorijuma za izvrsavanje operacija nad bazom podataka. Sadrzi metode za dodavanje, brisanje, izmenu, kao i izlistavanje podataka iz baze.
 *
 * S obzirom da se radi o generickoj implementaciji metode ove klase ce raditi za bilo koju domensku klasu koja implementira interfejs GenericEntity.
 *
 * Implementira interfejs DbRepository pri cemu postavlja tip na GenericEntity.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO " + entity.getTableName() + " VALUES " + entity.getInsertValues();
            System.out.println("[SQL Query Debug] Executing Query: " + query);
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement = entity.prepareStatement(statement, entity);
            statement.executeUpdate();
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                entity.setId(id);
            }
            rsKey.close();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List<GenericEntity> entities = new ArrayList<>();
        
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM " + param.getTableName();
        System.out.println("[SQL Query Debug] Executing Query: " + query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            GenericEntity genericEntity = param.selectObject(rs);
            entities.add(genericEntity);
        }
        rs.close();
        statement.close();
        
        return entities;
    }
    
    @Override
    public List<GenericEntity> getAllFiltered(GenericEntity param, long id) throws Exception {
        List<GenericEntity> entities = new ArrayList<>();
        
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM " + param.getTableName() + " WHERE " + param.getWhereFilteredCondition() + id;
        System.out.println("[SQL Query Debug] Executing Query: " + query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            GenericEntity genericEntity = param.selectObject(rs);
            entities.add(genericEntity);
        }
        rs.close();
        statement.close();
        
        return entities;
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String query = "UPDATE " + param.getTableName() + " SET " + param.getUpdateValues() + " WHERE " + param.getWhereCondition();
            System.out.println("[SQL Query Debug] Executing Query: " + query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String query = "DELETE FROM " + param.getTableName() + " WHERE " + param.getWhereCondition();
            System.out.println("[SQL Query Debug] Executing Query: " + query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<GenericEntity> findAll(GenericEntity param, String searchParameter) throws Exception {
        List<GenericEntity> entities = new ArrayList<>();
        
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM " + param.getTableName() + searchParameter;
        System.out.println("[SQL Query Debug] Executing Query: " + query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            GenericEntity genericEntity = param.selectObject(rs);
            entities.add(genericEntity);
        }
        rs.close();
        statement.close();
        
        return entities;
    }

}
