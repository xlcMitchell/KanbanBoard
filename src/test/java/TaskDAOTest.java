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

@Test
public void testCreateTaskWithEmptyName() {
    TaskDAO dao = new TaskDAO();
    
    // Creating a task with an empty string for the name
    Task emptyNameTask = new Task(0, "", "Testing empty name", null, "10-10-2026", 1, 1, 1, "Standard");

    // Execute
    boolean result = dao.createTask(emptyNameTask);

    // We expect false because the name is empty
    assertFalse("createTask should return false when task name is empty", result);
    System.out.println("Success: The DAO correctly blocked a task with an empty name.");
}

@Test
public void testUpdateTask() {
    TaskDAO dao = new TaskDAO();
    
    // Retrieve all tasks 
    List<Task> tasks = dao.getAllTasks();
    assertTrue("Database should not be empty for update test", !tasks.isEmpty());
    //Update 4th index which will be taskID 6
    Task taskToUpdate = tasks.get(4);
    String originalName = taskToUpdate.getTaskName();
    String newName = "Updated Name " + System.currentTimeMillis();
    
    //Add new task name to the object
    taskToUpdate.setTaskName(newName);
    
    // Execute Update
    boolean result = dao.updateTask(taskToUpdate);
    assertEquals("Update should return true on success", true, result);
    
    // Verify - Fetch it again to make sure it actually saved
    List<Task> updatedTasks = dao.getAllTasks();
    boolean foundUpdated = false;
    for (Task t : updatedTasks) {
        if (t.getTaskID() == taskToUpdate.getTaskID() && t.getTaskName().equals(newName)) {
            foundUpdated = true;
            break;
        }
    }
    
    assertTrue("The task name should be updated in the database", foundUpdated);
    System.out.println("Success: Task " + taskToUpdate.getTaskID() + " changed from '" + originalName + "' to '" + newName + "'");
}
}
