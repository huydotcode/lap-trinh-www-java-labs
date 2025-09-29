package iuh.fit.se.tuan05.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * Utility class để quản lý kết nối database
 * 
 * @author Student
 */
public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/QUANLYDIENTHOAI?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root"; // Thay đổi mật khẩu này
    
    private static Connection connection = null;
    
    /**
     * Lấy kết nối database
     * @return Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("Đã kết nối thành công đến MariaDB!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy MariaDB Driver!");
                throw new SQLException("Database driver not found", e);
            } catch (SQLException e) {
                System.err.println("Lỗi kết nối database: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
    
    /**
     * Đóng kết nối database
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đã đóng kết nối database!");
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối database: " + e.getMessage());
            }
        }
    }
    
    /**
     * Đóng ResultSet, Statement và Connection
     * @param rs ResultSet
     * @param stmt Statement
     * @param conn Connection
     */
    public static void closeResources(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng resources: " + e.getMessage());
        }
    }
    
    /**
     * Đóng PreparedStatement và Connection
     * @param pstmt PreparedStatement
     * @param conn Connection
     */
    public static void closeResources(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng resources: " + e.getMessage());
        }
    }
    
    /**
     * Đóng ResultSet, PreparedStatement và Connection
     * @param rs ResultSet
     * @param pstmt PreparedStatement
     * @param conn Connection
     */
    public static void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng resources: " + e.getMessage());
        }
    }
}
