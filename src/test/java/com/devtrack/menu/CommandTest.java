package com.devtrack.menu;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void commandIsExecuted() {
        // Simulates collateral effect
        AtomicBoolean executed = new AtomicBoolean(false);

        Command command = () -> executed.set(true);

        command.execute();

        assertTrue(executed.get(), "Command should have been executed");
    }
}