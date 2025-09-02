package iuh.fit.se.bai03.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Database {
    private static final String JDBC_URL =  "jdbc:mariadb://localhost:3306/uploadfileservletdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            // Load driver
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
