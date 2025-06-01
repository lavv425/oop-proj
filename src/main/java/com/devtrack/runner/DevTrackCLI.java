package com.devtrack.runner;

import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.ConsoleStyle;
import com.devtrack.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DevTrackCLI {

    @Autowired
    private ProjectServiceImpl projectService;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleStyle.CYAN + ConsoleStyle.BOLD + "\n=== Welcome in DevTrack CLI ===" + ConsoleStyle.RESET);

        Menu mainMenu = new Menu("Main Menu");
        mainMenu.addOption("1", "Create a Project", new CreateProjectCommand(projectService));
        mainMenu.addOption("2", "Manage Projects", new SubMenuCommand(createProjectMenu()));
        mainMenu.addOption("3", "EXIT", scanner1 -> System.out.println("Goodbye!"), true);

        mainMenu.display(scanner);
    }

    private Menu createProjectMenu() {
        Menu projectMenu = new Menu("Manage Projects");
        projectMenu.addOption("1", "List Projects", new ListProjectsCommand(projectService));
        projectMenu.addOption("2", "Delete a Project", new DeleteProjectCommand(projectService));
        projectMenu.addOption("3", "Add Milestone", new AddMilestoneCommand(projectService));
        projectMenu.addOption("4", "List Milestones", new ListMilestonesCommand(projectService));
        projectMenu.addOption("5", "Add Task to a Milestone", new AddTaskCommand(projectService));
        projectMenu.addOption("6", "View Tasks", new ViewTasksCommand(projectService));
        projectMenu.addOption("0", "Go Back", scanner -> {});
        return projectMenu;
    }
}