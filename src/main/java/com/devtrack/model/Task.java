package com.devtrack.model;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public String getDescription() {
        return description;
    }
}