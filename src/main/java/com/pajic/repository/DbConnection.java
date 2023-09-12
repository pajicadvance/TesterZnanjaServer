/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.repository;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Pavle
 */
public class DbConnection {
    private java.sql.Connection connection;
    private static DbConnection instance;

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public java.sql.Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("./database.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
