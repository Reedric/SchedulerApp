package com.example.schedulerapp.ui.classes;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private static int nextId = 1;

    private int id;
    private String name;
    private String startTime;
    private String endTime;
    private String professorName;


    // Constructor for creating a new class with auto-incremented id
    public ClassInfo(String name, String startTime, String endTime, String professorName) {
        this.id = nextId++;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.professorName = professorName;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    @Override
    public String toString() {
        return name + " - " + startTime + " to " + endTime + ", Prof: " + professorName;
    }
}
