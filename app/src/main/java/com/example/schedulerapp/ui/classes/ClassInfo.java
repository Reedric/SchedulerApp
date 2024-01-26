package com.example.schedulerapp.ui.classes;

public class ClassInfo {
    private String name;
    private String startTime;
    private String endTime;
    private String professorName;

    public ClassInfo(String name, String startTime, String endTime, String professorName) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.professorName = professorName;
    }

    // Getters and Setters
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
