package com.devtrack.interfaces;

import java.util.Scanner;

@FunctionalInterface
public interface MenuCommand {
    void execute(Scanner scanner);
}