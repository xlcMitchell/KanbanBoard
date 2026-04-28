/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Kiwit
 */
public class DBConnection {
    // Standard path as per your assessment brief
    private static final String URL = "jdbc:sqlite:Kanban.db";
    private static Connection conn = null;

    /**
     * Gets the current connection. If it doesn't exist, it creates one.
     */
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Load the driver 
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(URL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Error: " + e.getMessage());
        }
    }
}
