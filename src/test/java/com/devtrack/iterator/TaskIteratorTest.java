package com.devtrack.iterator;

import com.devtrack.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskIteratorTest {

    private TaskIterator iterator;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Task("Task Test 1");
        task2 = new Task("Task Test 2");
        iterator = new TaskIterator(List.of(task1, task2));
    }

    @Test
    void hasNext_shouldReturnTrue_whenElementsRemain() {
        assertTrue(iterator.hasNext()); // index = 0
        iterator.next(); // index = 1
        assertTrue(iterator.hasNext()); // index = 1
    }

    @Test
    void hasNext_shouldReturnFalse_whenAllElementsIterated() {
        iterator.next(); // index = 1
        iterator.next(); // index = 2
        assertFalse(iterator.hasNext());
    }

    @Test
    void next_shouldReturnTasksInOrder() {
        Task first = iterator.next();
        Task second = iterator.next();

        assertEquals("Task Test 1", first.getDescription());
        assertEquals("Task Test 2", second.getDescription());
    }

    @Test
    void next_shouldThrowException_ifNoMoreElements() {
        iterator.next();
        iterator.next();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iterator.next();
        });
    }
}