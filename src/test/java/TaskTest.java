/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kiwit
 */
import org.junit.Test;
import static org.junit.Assert.*;
import Model.Task; 
/**
 * JUnit test class for Task model.
 * Maps to Test ID 8 in the Developer Test Plan.
 */
public class TaskTest {

    /**
     * White box Test: test all task getter methods (Test ID 8)
     */
    @Test
    public void testTaskGettersAndSetters() {
        // Arrange: Create task object with the test data
        int testId = 1;
        String testName = "set up database";
        String testDesc = "Setup Kanban board database";
        String testDate = "10/10/2026";
        int testUser = 1; // Assuming 'Alex Alex' has ID 1
        String testSwimlane = "Standard";

        Task task = new Task();
        
        // 2.set task variables to mock test data from developer testing spreadsheet
        task.setTaskID(testId);
        task.setTaskName(testName);
        task.setTaskDescription(testDesc);
        task.setDueDate(testDate);
        task.setAssignedUser(testUser);
        task.setSwimlane(testSwimlane);

        // Assert: Verify every getter method matches the input
        assertEquals("Task ID should match", testId, task.getTaskID());
        assertEquals("Task name should match", testName, task.getTaskName());
        assertEquals("Description should match", testDesc, task.getTaskDescription());
        assertEquals("Due date should match", testDate, task.getDueDate());
        assertEquals("Assigned user should match", testUser, task.getAssignedUser());
        assertEquals("Swimlane should match", testSwimlane, task.getSwimlane());
    }

    /**
     * White box Test: Full Constructor Verification
     * Ensures the 'Full' constructor correctly maps all fields at once.
     */
    
    @Test
    public void testFullConstructor() {
        Task task = new Task(5, "Test UI", "Build GUI", "2026-04-28", "30-04-2026", 2, 1, 1, "Expedite");
        
        assertEquals(5, task.getTaskID());
        assertEquals("Test UI", task.getTaskName());
        assertEquals("Expedite", task.getSwimlane());
    }

    /**
     * Boundary Test: Empty/Null values
     * Checks how the object handles empty strings (Linked to Test ID 1 in spreadsheet)
     */
    @Test
    public void testTaskNameBoundary() {
        Task task = new Task();
        task.setTaskName(""); // Testing empty boundary
        assertEquals("", task.getTaskName());
    }
}
