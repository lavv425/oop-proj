package com.devtrack.menu;

import java.util.Scanner;

@FunctionalInterface
public interface MenuCommand {
    void execute(Scanner scanner);
}