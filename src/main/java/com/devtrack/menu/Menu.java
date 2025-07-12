package com.devtrack.menu;

import com.devtrack.interfaces.Command;
import com.devtrack.interfaces.MenuCommand;
import com.devtrack.utils.ConsoleStyle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents an interactive menu system with configurable menu items and
 * actions.
 * Allows dynamic addition of menu options and provides a display mechanism for
 * user interaction.
 * Supports different types of menu commands and provides flexibility in menu
 * navigation.
 */
public class Menu {
    private final String title;
    private final boolean testMode;
    private final Map<String, MenuItem> items = new LinkedHashMap<>();

    public Menu(String title) {
        this(title, false);
    }

    public Menu(String title, boolean testMode) {
        this.title = title;
        this.testMode = testMode;
    }

    public void addItem(String key, String description, MenuCommand action, boolean exitAfter) {
        items.put(key, new MenuItem(description, action, exitAfter));
    }

    // Commands using directly the Scanner
    public void addOption(String key, String description, MenuCommand action, boolean exitAfter) {
        addItem(key, description, action, exitAfter);
    }

    public void addOption(String key, String description, MenuCommand action) {
        addOption(key, description, action, false);
    }

    // Simple command w/o the Scanner
    public void addOption(String key, String description, Command action) {
        addItem(key, description, scanner -> action.execute(), false);
    }

    public void display(Scanner scanner) {
        while (true) {
            System.out.println(ConsoleStyle.BOLD + ConsoleStyle.CYAN + "\n== " + title + " ==" + ConsoleStyle.RESET);
            for (Map.Entry<String, MenuItem> entry : items.entrySet()) {
                System.out.println(ConsoleStyle.YELLOW + entry.getKey() + ". " + ConsoleStyle.RESET
                        + entry.getValue().description);
            }

            System.out.print("\nChoose an option: ");

            if (!scanner.hasNextLine())
                break;
            String choice = scanner.nextLine().trim();

            MenuItem item = items.get(choice);

            if (item != null) {
                item.action.execute(scanner);

                if (item.exitAfter) {
                    System.out.println("Exiting...");
                    if (!testMode)
                        System.exit(0); // bypass in test
                    break;
                }

                if (choice.equals("0")) {
                    break;
                }

            } else {
                System.out.println(ConsoleStyle.RED + "Invalid option." + ConsoleStyle.RESET);
            }
        }
    }

    private static class MenuItem {
        String description;
        MenuCommand action;
        boolean exitAfter;

        MenuItem(String description, MenuCommand action, boolean exitAfter) {
            this.description = description;
            this.action = action;
            this.exitAfter = exitAfter;
        }
    }
}