/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 */

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private int listID;
    private String name;
    private List<Task> tasks; // 0..* relationship from your diagram

    public TaskList(int listID,String name) {
        this.listID = listID;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public List<Task> getTasks() { return tasks; }
    public void addTask(Task t) { this.tasks.add(t); }
    public void clearTasks() { this.tasks.clear(); }
    public int getListID() { 
    return listID; 
}

public void setListID(int listID) { 
    this.listID = listID; 
}
}