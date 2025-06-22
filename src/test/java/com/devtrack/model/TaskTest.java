package com.devtrack.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void constructor_shouldSetDescriptionAndDefaultToNotDone() {
        Task task = new Task("Test Write unit tests");

        assertEquals("Test Write unit tests", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void setDone_shouldSetTaskAsDone() {
        Task task = new Task("Test set as done");

        task.setDone();

        assertTrue(task.isDone());
    }

    @Test
    void getDescription_shouldReturnCorrectValue() {
        Task task = new Task("Test returns correct");

        assertEquals("Test returns correct", task.getDescription());
    }
}