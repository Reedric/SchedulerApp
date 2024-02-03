package com.example.schedulerapp.ui.assignment;


public class AssignmentInfo {
    private String name;
    private String courseName;
    private String dueDate;


    public AssignmentInfo(String name, String courseName, String dueDate) {
        this.name = name;
        this.courseName = courseName;
        this.dueDate = dueDate;
    }


    public String getName() {
        return name;
    }


    public String getCourseName() {
        return courseName;
    }


    public String getDueDate() {
        return dueDate;
    }
}
