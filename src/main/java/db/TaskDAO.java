/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author Kiwit
 * The TaskDAO handles SQLite database operations
 * This ensures the rest of the software is from database operations
 * Making it easy to change the database in the future
 */
import Model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskDAO {

    // Insert new task
    public boolean createTask(Task task) {
        //make sure due date is valid format
       if (!isValidDate(task.getDueDate())) {
        System.err.println("Validation Error: Invalid date format: " + task.getDueDate());
        return false; // don't insert any data
    }
       
       // --- Check the task name is valid---
    if (!isValidTaskName(task.getTaskName())) {
        System.err.println("Validation Fail: Task name cannot be empty.");
        return false; 
    }
        
        String sql = "INSERT INTO task (taskName, taskDescription, dueDate, assignedUser,taskList, position, swimlane) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, task.getTaskName());
            pstmt.setString(2, task.getTaskDescription());
            pstmt.setString(3, task.getDueDate());
            pstmt.setInt(4, task.getAssignedUser());
            pstmt.setInt(5, task.getColumnID());
            pstmt.setInt(6, task.getPosition());
            pstmt.setString(7, task.getSwimlane());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creating task: " + e.getMessage());
            return false;
        }
    }

    // Read all tasks from datbase
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tasks.add(new Task(
                    rs.getInt("taskID"),
                    rs.getString("taskName"),
                    rs.getString("taskDescription"),
                    rs.getString("timestamp"),
                    rs.getString("dueDate"),
                    rs.getInt("assignedUser"),
                    rs.getInt("taskList"),
                    rs.getInt("position"),
                    rs.getString("swimlane")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // update task
    public boolean updateTask(Task task) {
        
           //make sure due date is valid format
       if (!isValidDate(task.getDueDate())) {
        System.err.println("Validation Error: Invalid date format: " + task.getDueDate());
        return false; // don't insert any data
    }
       
       // --- Check the task name is valid---
    if (!isValidTaskName(task.getTaskName())) {
        System.err.println("Validation Fail: Task name cannot be empty.");
        return false; 
    }
        String sql = "UPDATE task SET taskName=?, taskDescription=?, dueDate=?, assignedUser=?,taskList=?, position=?, swimlane=? WHERE taskID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, task.getTaskName());
            pstmt.setString(2, task.getTaskDescription());
            pstmt.setString(3, task.getDueDate());
            pstmt.setInt(4, task.getAssignedUser());
            pstmt.setInt(5, task.getColumnID());
            pstmt.setInt(6, task.getPosition());
            pstmt.setString(7, task.getSwimlane());
            pstmt.setInt(8, task.getTaskID());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
            return false;
        }
    }

    //Delete task
    public boolean deleteTask(int taskID) {
        String sql = "DELETE FROM task WHERE taskID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, taskID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
            return false;
        }
    }
    
    private boolean isValidDate(String dateStr) {
    if (dateStr == null) return false;
    try {
        // check that it matches database date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter.parse(dateStr);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}
    
    private boolean isValidTaskName(String name) {
    //Make sure the name field is not empty or null
    return name != null && !name.trim().isEmpty();
}
}