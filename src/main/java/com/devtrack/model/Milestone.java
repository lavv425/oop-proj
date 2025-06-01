package com.devtrack.model;

import java.util.ArrayList;
import java.util.List;

public class Milestone {
    private String title;
    private List<Task> tasks = new ArrayList<>();

    public Milestone(String title) {
        this.title = title;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getTitle() {
        return title;
    }
}