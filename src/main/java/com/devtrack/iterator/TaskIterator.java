package com.devtrack.iterator;

import com.devtrack.model.Task;

import java.util.Iterator;
import java.util.List;

public class TaskIterator implements Iterator<Task> {
    private final List<Task> tasks;
    private int index = 0;

    public TaskIterator(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return index < tasks.size();
    }

    @Override
    public Task next() {
        return tasks.get(index++);
    }
}