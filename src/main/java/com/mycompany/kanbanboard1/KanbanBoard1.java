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
import GUI.MainBoard;
import GUI.MainBoardExperiment;
import db.DBConnection;
import java.sql.Connection;
import Controller.BoardController;
import db.TaskDAO;
import db.UserDAO;
import java.util.List;
import Model.Task;
import Model.User;

public class KanbanBoard1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TaskDAO object test
        TaskDAO dao = new TaskDAO();
        List<Task> taskList = dao.getAllTasks();
    
        if (taskList != null && !taskList.isEmpty()) {
             System.out.println("SUCCESS: Found " + taskList.size() + " tasks!");}
        
        //UserDAO Test
        UserDAO userD = new UserDAO();
        List <User> userList = userD.getAllUsers();
        
        if(userList != null && !userList.isEmpty()){
        System.out.println("SUCCESS: Found " + userList.size() + " users!");}
      /*  
        java.awt.EventQueue.invokeLater(() -> {
        MainBoard frame = new MainBoard();
        frame.setVisible(true);
    }); */
        //display kanban board
         java.awt.EventQueue.invokeLater(() -> {
        MainBoardExperiment experimentframe = new MainBoardExperiment();
        experimentframe.setVisible(true);
        //Initialise boardcontroller
        BoardController controller = new BoardController(experimentframe);
        
        controller.refreshBoard();
    });
    }
    
    
    
}
