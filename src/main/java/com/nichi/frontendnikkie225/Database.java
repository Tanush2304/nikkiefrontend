package com.nichi.frontendnikkie225;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class Database {

    private static final String URL="jdbc:postgresql://192.168.1.92:5432/marketinfo";
    private static final String USERNAME="esquire";
    private static final String PASSWORD="password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);

    }
}
