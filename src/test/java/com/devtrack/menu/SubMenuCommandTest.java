package com.devtrack.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class SubMenuCommandTest {

    private Menu submenu;
    private SubMenuCommand command;

    @BeforeEach
    void setUp() {
        submenu = mock(Menu.class);
        command = new SubMenuCommand(submenu);
    }

    @Test
    void executesSubmenuDisplay() {
        String input = "0\n"; // simulated "exit" directtly
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        command.execute(scanner);

        verify(submenu, times(1)).display(scanner);
    }
}