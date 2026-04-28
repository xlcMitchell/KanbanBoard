/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kiwit
 */
public class Task {
    private int taskID;
    private String taskName;
    private String taskDescription;
    private String timestamp;
    private String dueDate;
    private int assignedUser; // References UserID
    private int columnID;     // 1=Requested, 2=In Progress, 3=Done
    private int position;     // Order within the column
    private String swimlane;  // "Standard" or "Expedite"

    // Default Constructor
    public Task() {}

    // Full Constructor for loading from DAO
    public Task(int taskID, String taskName, String taskDescription, String timestamp, 
                String dueDate, int assignedUser, int columnID, int position, String swimlane) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.timestamp = timestamp;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
        this.columnID = columnID;
        this.position = position;
        this.swimlane = swimlane;
    }

    // Getters and Setters
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) { this.taskID = taskID; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getTaskDescription() { return taskDescription; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public int getAssignedUser() { return assignedUser; }
    public void setAssignedUser(int assignedUser) { this.assignedUser = assignedUser; }

    public int getColumnID() { return columnID; }
    public void setColumnID(int columnID) { this.columnID = columnID; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public String getSwimlane() { return swimlane; }
    public void setSwimlane(String swimlane) { this.swimlane = swimlane; }

    @Override
    public String toString() {
        return "Task #" + taskID + ": " + taskName;
    }
}
