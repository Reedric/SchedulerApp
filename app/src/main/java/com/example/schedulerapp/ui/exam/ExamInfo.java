package com.example.schedulerapp.ui.exam;

import java.io.Serializable;
public class ExamInfo implements Serializable {
    private static int nextId = 1;
    private int id;
    private String name;
    private String location;
    private String date;
    private String time;

    public ExamInfo(String name, String location, String date, String time) {
        this.id = nextId++;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    // Setters, if needed
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
