package com.devtrack.factory;

import com.devtrack.menu.*;
import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.ConsoleStyle;

public class MenuFactory {

    public static Menu createMainMenu(ProjectServiceImpl service) {
        System.out.println(ConsoleStyle.CYAN + ConsoleStyle.BOLD + "\n=== Welcome in DevTrack CLI ===" + ConsoleStyle.RESET);

        Menu menu = new Menu("Main Menu");

        menu.addOption("1", "Create a Project", new CreateProjectCommand(service));
        menu.addOption("2", "Manage Projects", new SubMenuCommand(createProjectManagementMenu(service)));
        menu.addOption("3", "Exit", s -> System.exit(0), true);

        return menu;
    }

    public static Menu createProjectManagementMenu(ProjectServiceImpl service) {
        Menu submenu = new Menu("Manage Projects");

        submenu.addOption("1", "List Projects", new ListProjectsCommand(service));
        submenu.addOption("2", "Delete a Project", new DeleteProjectCommand(service));
        submenu.addOption("3", "Add Milestone", new AddMilestoneCommand(service));
        submenu.addOption("4", "List Milestones", new ListMilestonesCommand(service));
        submenu.addOption("5", "Add Task to a Milestone", new AddTaskCommand(service));
        submenu.addOption("6", "View Tasks", new ViewTasksCommand(service));
        submenu.addOption("0", "Go Back", scanner -> {
        });

        return submenu;
    }
}