package com.crudmysql.crud.maven.netbeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Ajusta URL, USER y PASS según tu instalación de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/crud_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // cambia si usas otro usuario
    private static final String PASS = "";     // pon tu contraseña

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC no encontrado: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
