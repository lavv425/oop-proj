package com.devtrack.menu;

import java.util.Scanner;

public class SubMenuCommand implements MenuCommand {
    private final Menu submenu;

    public SubMenuCommand(Menu submenu) {
        this.submenu = submenu;
    }

    @Override
    public void execute(Scanner scanner) {
        submenu.display(scanner);
    }
}