/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
The UserDAO class is used to retrieve user data from the database
The user data will not need to be modified for this iteration
It is only required for managing tasks
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.User;

public class UserDAO {

    /**
     * Retrieves all users.
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT userID, userName, email FROM User";

        // Calling your existing DBConnection.getConnection() method
        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("userID"),
                    rs.getString("userName"),
                    rs.getString("email")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("UserDAO Error: " + e.getMessage());
        }
        return userList;
    }
}