package com.devtrack.menu;

import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.ConsoleStyle;

import java.util.List;
import java.util.Scanner;

public class ListMilestonesCommand implements MenuCommand {
    private final ProjectServiceImpl projectService;

    public ListMilestonesCommand(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @Override
    public void execute(Scanner scanner) {
        List<ProjectDocument> projects = projectService.listProjects();
        if (projects.isEmpty()) {
            System.out.println(ConsoleStyle.RED + "\nNessun progetto disponibile." + ConsoleStyle.RESET);
            return;
        }

        for (ProjectDocument project : projects) {
            System.out.println(ConsoleStyle.BOLD + "\nProgetto: " + project.getName() + ConsoleStyle.RESET);
            project.getMilestones().forEach(m ->
                System.out.println(" - " + m.getTitle() + " (" + m.getTasks().size() + " tasks)"));
        }
    }
}