package com.devtrack.menu;

import com.devtrack.utils.ConsoleStyle;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void executesCorrectMenuCommand() {
        MenuCommand mockCommand = mock(MenuCommand.class);

        Menu menu = new Menu("Test Menu", true); // testMode = true
        menu.addOption("1", "Say Hello", mockCommand);

        String input = "1\n0\n"; // choose option 1, then 0 to exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        menu.display(scanner);

        verify(mockCommand, times(1)).execute(scanner);

        String output = stripAnsi(outContent.toString());

        assertTrue(output.contains("== Test Menu =="));
        assertTrue(output.contains("1. Say Hello"));
    }

    @Test
    void printsInvalidOption() {
        MenuCommand mockCommand = mock(MenuCommand.class);

        Menu menu = new Menu("Test Menu", true);
        menu.addOption("1", "Valid option", mockCommand);

        String input = "X\n0\n"; // invalid option, then exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        menu.display(scanner);

        verify(mockCommand, never()).execute(any());
        assertTrue(outContent.toString().contains(ConsoleStyle.RED + "Invalid option." + ConsoleStyle.RESET));
    }

    @Test
    void exitsWhenExitAfterIsTrue() {
        MenuCommand mockCommand = mock(MenuCommand.class);

        Menu menu = new Menu("Exit Menu", true); // test mode prevents System.exit
        menu.addOption("9", "Exit now", mockCommand, true);

        String input = "9\n"; // triggers exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        menu.display(scanner);

        verify(mockCommand, times(1)).execute(scanner);
        assertTrue(outContent.toString().contains("Exiting..."));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private String stripAnsi(String input) {
        return input.replaceAll("\\u001B\\[[;\\d]*m", "");
    }
}