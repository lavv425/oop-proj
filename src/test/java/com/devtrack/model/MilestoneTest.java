package com.devtrack.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MilestoneTest {

    @Test
    void constructor_shouldSetTitle() {
        Milestone m = new Milestone("Test Milestone 1.0");
        assertEquals("Test Milestone 1.0", m.getTitle());
    }

    @Test
    void addTask_shouldAddToList() {
        Milestone m = new Milestone("Backend");
        Task t = new Task("Implement List Test");
        m.addTask(t);

        List<Task> tasks = m.getTasks();
        assertEquals(1, tasks.size());
        assertSame(t, tasks.get(0));
    }

    @Test
    void getTasks_shouldReturnEmptyListInitially() {
        Milestone m = new Milestone("Init Test");
        assertTrue(m.getTasks().isEmpty());
    }
}