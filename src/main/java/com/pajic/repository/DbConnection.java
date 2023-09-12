/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.repository;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Predstavlja klasu za kreiranje konekcija sa bazom. Implementira singleton patern.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class DbConnection {
    /**
     * Konekcija na bazu podataka.
     */
    private java.sql.Connection connection;
    /**
     * Jedinstvena instanca DbConnection klase.
     */
    private static DbConnection instance;

    /**
     * Privatni konstruktor koji se poziva samo pri inicijalnom kreiranju jedinstvene instance ove klase.
     */
    private DbConnection() {
    }

    /**
     * Kreira instancu klase DbConnection ukoliko ona ne postoji, u suprotnom vraca postojecu instancu.
     * @return instance - Upravo kreirana ili postojeca instanca klase DbConnection
     */
    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    /**
     * Vraca konekciju koja postoji sa bazom podataka ili ako ne postoji kreira je na osnovu parametara koji se nalaze u fajlu database.properties.
     * @return connection - Konekcija sa bazom podataka.
     * @throws Exception - U slucaju da nastane greska prilikom ostvarivanja konekcije sa bazom podataka.
     */
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
