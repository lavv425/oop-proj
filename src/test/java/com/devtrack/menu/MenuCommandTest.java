package com.devtrack.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuCommandTest {

    @Test
    void lambdaCanBeExecuted() {
        AtomicBoolean called = new AtomicBoolean(false);

        MenuCommand command = scanner -> {
            scanner.nextLine(); // simulates read
            called.set(true);
        };

        String input = "hello\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        command.execute(scanner);

        assertTrue(called.get(), "Lambda called correctly.");
    }
}