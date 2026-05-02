/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Kiwit
 */
import java.util.List;
import java.util.ArrayList;
import db.TaskDAO;
import db.UserDAO;
import GUI.MainBoardExperiment;
import Model.Task;
import Model.TaskList;
public class BoardController {
  
    private TaskDAO taskDAO;
    private UserDAO userDAO;
    private List<TaskList> columnLists; // Represents the 0..* TaskList relationship
    private MainBoardExperiment boardView; // GUI reference

    /**
     * @param board The GUI Board instance to control
     */
    public BoardController(MainBoardExperiment board) {
        this.boardView = board;
        this.taskDAO = new TaskDAO();
        this.userDAO = new UserDAO();
        this.columnLists = new ArrayList<>();
        
        // Initialize lists for To Do, In progress, Done
        columnLists.add(new TaskList(1,"To Do"));
        columnLists.add(new TaskList(2,"In Progress"));
        columnLists.add(new TaskList(3,"Done"));
    }

    
     //Retrieves all tasks from the database via the DAO.
    
    public void fetchTasks() {
        List<Task> allTasks = taskDAO.getAllTasks();
        System.out.println("fetchTasks() method " + allTasks.size());
        distributeTasksToLists(allTasks); //add to correct task list
    }

 
     //Helper to sort tasks into the correct TaskList based on status/column
    
  private void distributeTasksToLists(List<Task> tasks) {
    // Clear existing lists to avoid duplicates on refresh
    for (TaskList list : columnLists) {
        list.getTasks().clear();
    }

    for (Task t : tasks) {
        // Match by Integer ID instead of String Name
        for (TaskList list : columnLists) {
            System.out.println("Checking Task [" + t.getTaskName() + "] ColumnID: " + t.getColumnID() + " against ListID: " + list.getListID());
            // comparing int to int is much faster!
            if (list.getListID() == t.getColumnID()) { 
                list.getTasks().add(t);
                break; // Found the match, stop looking for this task
            }
        }
    }
}
   //updates the board
    public void refreshBoard() {
        System.out.println("--- Refreshing Board ---");
        fetchTasks();
        boardView.displayTasks(columnLists); // Passing data back to the View
    }

    
    public void handleMoveTask(Task task, int newColumn,boolean isExpedite) {
        task.setColumnID(newColumn);
        boolean success = taskDAO.moveTask(task.getTaskID(), newColumn,isExpedite);
        
        if (success) {
            refreshBoard(); // Update UI after database change
        }
    }

    //add task from taskDAO if sucessfull the board is refreshed
    public void handleAddTask(Task newTask) {
        if (taskDAO.createTask(newTask)) {
            refreshBoard();
        }
    }

    //Delete task from board if returns true the board refreshed
    public void handleDeleteTask(int taskID) {
        if (taskDAO.deleteTask(taskID)) {
            refreshBoard();
        }
    }
}