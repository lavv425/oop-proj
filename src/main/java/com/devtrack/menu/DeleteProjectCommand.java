package com.devtrack.menu;

import com.devtrack.interfaces.MenuCommand;
import com.devtrack.model.ProjectDocument;
import com.devtrack.security.ExceptionShield;
import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.LoggerUtil;
import org.slf4j.Logger;

import java.util.List;
import java.util.Scanner;

public class DeleteProjectCommand implements MenuCommand {
    private static final Logger logger = LoggerUtil.getLogger(DeleteProjectCommand.class);
    private final ProjectServiceImpl projectService;

    public DeleteProjectCommand(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @Override
    public void execute(Scanner scanner) {
        logger.info("User initiated project deletion.");
        List<ProjectDocument> projects = projectService.listProjects();

        if (projects == null || projects.isEmpty()) {
            logger.warn("No projects found to delete.");
            System.out.println("No projects available.");
            return;
        }

        System.out.println("\nSelect a project to delete:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, projects.get(i).getName());
        }

        System.out.print("Project number: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            ExceptionShield.handle(e);
            logger.error("Invalid number format for input '{}'", input);
            return;
        }

        if (index < 0 || index >= projects.size()) {
            logger.warn("User selected an out-of-range project index: {}", index);
            System.out.println("Invalid selection.");
            return;
        }

        ProjectDocument selected = projects.get(index);
        logger.info("Attempting to delete project: {} ({})", selected.getName(), selected.getId());

        boolean deleted = projectService.deleteProjectById(selected.getId());

        if (deleted) {
            logger.info("Project successfully deleted: {}", selected.getName());
            System.out.println("Project deleted.");
        } else {
            logger.error("Failed to delete project: {}", selected.getName());
            System.out.println("Deletion failed.");
        }
    }
}