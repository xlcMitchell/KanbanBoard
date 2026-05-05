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
    
    /**
 * Retrieves the full name of a user based on their unique ID.
 * 
 * @param userId The ID retrieved from the task object.
 * @return The String name of the user, or "Unassigned" if not found.
 */
public String getUserNameById(int userId) {
    System.out.println("DEBUG: Searching for User ID: " + userId); // ADD THIS
    String name = "Unassigned";
    // We use a WHERE clause to find the specific user
    String sql = "SELECT userName FROM user WHERE userID = " + userId;

    try (Connection conn = DBConnection.getConnection(); 
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            // Retrieve the userName string from the result
            name = rs.getString("userName");
        }
    } catch (SQLException e) {
        System.err.println("UserDAO Lookup Error: " + e.getMessage());
    }
    return name;}
}