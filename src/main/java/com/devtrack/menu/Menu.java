package com.devtrack.menu;

import com.devtrack.utils.ConsoleStyle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final String title;
    private final Map<String, MenuItem> items = new LinkedHashMap<>();

    public Menu(String title) {
        this.title = title;
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
            String choice = scanner.nextLine().trim();
            MenuItem item = items.get(choice);

            if (item != null) {
                item.action.execute(scanner);

                if (item.exitAfter) {
                    System.out.println("Exiting...");
                    System.exit(0);
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