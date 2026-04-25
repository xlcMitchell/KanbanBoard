/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kanbanboard1;

/**
 *
 * @author Kiwit
 */
import java.sql.DriverManager;
import java.sql.SQLException;


public class KanbanBoard1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //reference to db stored in project folder
        var url = "jdbc:sqlite:Kanban.db";
        var sql = "SELECT * FROM Cards"; //sql query for Kanban.db to return all rows

      
        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("%-5s %-25s %-15s %-15s %-12s %-12s %-20s%n",
                        rs.getInt("cardID"),
                        rs.getString("details"),
                        rs.getString("owner"),
                        rs.getString("holder"),
                        rs.getString("status"),
                        rs.getString("swimlane"),
                        rs.getString("timestamp")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
