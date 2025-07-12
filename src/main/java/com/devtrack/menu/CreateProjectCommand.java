package com.devtrack.menu;

import com.devtrack.interfaces.MenuCommand;
import com.devtrack.interfaces.ProjectService;
import com.devtrack.security.ExceptionShield;
import com.devtrack.security.InputSanitizer;
import com.devtrack.utils.LoggerUtil;

import java.util.Scanner;

import org.slf4j.Logger;

public class CreateProjectCommand implements MenuCommand {
    private static final Logger logger = LoggerUtil.getLogger(CreateProjectCommand.class);
    private final ProjectService projectService;

    public CreateProjectCommand(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter project name: ");
            String name = scanner.nextLine().trim();
            String sanitized = InputSanitizer.sanitize(name);

            if (sanitized.isEmpty()) {
                logger.warn("Empty or invalid project name entered.");
                System.out.println("Project name cannot be empty or contain invalid characters.");
                return;
            }

            if (projectService.listProjects().stream()
                    .anyMatch(p -> p.getName().equalsIgnoreCase(sanitized))) {
                logger.warn("A project with the name '{}' already exists.", sanitized);
                System.out.println("A project with this name already exists.");
                return;
            }

            projectService.createProject(sanitized);
            logger.info("Project created: {}", sanitized);

        } catch (Exception e) {
            ExceptionShield.handle(e);
            logger.error("Exception while creating project", e);
        }
    }
}