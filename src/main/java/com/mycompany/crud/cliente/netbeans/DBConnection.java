package com.mycompany.crud.cliente.netbeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/crud_db";
    private static final String USER = "root";      // tu usuario de MySQL
    private static final String PASSWORD = "";      // tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
