/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kiwit
 */
import db.TaskDAO;
import Model.Task;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TaskDAOTest {

    @Test
    public void testDatabaseIntegration() {
        TaskDAO dao = new TaskDAO();
        
        
        
     Task newTask = new Task(0, "Design Wire Frames", "JUnit Test",null, "10-10-2026", 1, 1, 1, "Standard");

        // Test the Create (Insert)
        boolean created = dao.createTask(newTask);
        assertTrue("Database should allow task insertion into 'Task' table", created);

        // Test the Read (Select)
        List<Task> list = dao.getAllTasks();
        assertNotNull("The list should be retrieved from the 'Task' table", list);
        
        boolean isFound = false;
        for (Task t : list) {
            if (t.getTaskName().equals("Design Wire Frames")) {
                isFound = true;
                break;
            }
        }
        
        assertTrue("The inserted task should be found in the database", isFound);
        System.out.println("Success: Table 'Task' is working correctly!");
    }
    
    @Test
public void testDeleteSpecificTask() {
    TaskDAO dao = new TaskDAO();
    int idToDelete = 5; 

    //Execute the delete
    boolean result = dao.deleteTask(idToDelete);

    //Assert that the DAO successfully removed a row
    assertTrue("Task ID 5 should be successfully deleted from the Task table", result);

    //Verify it's actually gone from the database
    List<Task> allTasks = dao.getAllTasks();
    boolean found = false;
    for (Task t : allTasks) {
        if (t.getTaskID() == idToDelete) {
            found = true;
            break;
        }
    }

    assertFalse("Task ID 5 should no longer appear in the task list", found);
    System.out.println("Success: Task ID 5 has been deleted from the database.");
}

@Test
public void testCreateTaskWithWrongDateFormat() {
    TaskDAO dao = new TaskDAO();
    
   
    Task wrongFormatTask = new Task(
        0, 
        "Design Wire Frames", 
        "JUnit Test", 
        null, 
        "2026-10-10", // wrong date format
        1, 1, 1, "Standard"
    );

    // 1. Execute the insertion
    boolean result = dao.createTask(wrongFormatTask);

    //Assert that it FAILED
    assertFalse("The DAO should reject dates with slashes (10/10/2026)", result);
    
    System.out.println("Success: The DAO correctly blocked the incorrect date format.");
}
}
