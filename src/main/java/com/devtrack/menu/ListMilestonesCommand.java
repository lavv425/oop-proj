package com.devtrack.menu;

import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.ConsoleStyle;

import java.util.List;
import java.util.Scanner;

/**
 * Command to list milestones for available projects.
 * 
 * This command retrieves all projects and displays their milestones,
 * showing the milestone title and the number of tasks for each milestone.
 * If no projects are available, it displays a message indicating no projects exist.
 */
public class ListMilestonesCommand implements MenuCommand {
    private final ProjectServiceImpl projectService;

    public ListMilestonesCommand(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @Override
    public void execute(Scanner scanner) {
        List<ProjectDocument> projects = projectService.listProjects();
        if (projects.isEmpty()) {
            System.out.println(ConsoleStyle.RED + "\nNo project available." + ConsoleStyle.RESET);
            return;
        }

        for (ProjectDocument project : projects) {
            System.out.println(ConsoleStyle.BOLD + "\nProject: " + project.getName() + ConsoleStyle.RESET);
            project.getMilestones().forEach(m ->
                System.out.println(" - " + m.getTitle() + " (" + m.getTasks().size() + " tasks)"));
        }
    }
}