package com.devtrack.menu;

import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AddMilestoneCommand implements MenuCommand {
    private final ProjectServiceImpl service;

    public AddMilestoneCommand(ProjectServiceImpl service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
        List<ProjectDocument> projects = service.listProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }
        for (int i = 0; i < projects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, projects.get(i).getName());
        }

        System.out.print("Select project number: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (idx < 0 || idx >= projects.size()) {
            System.out.println("Invalid index.");
            return;
        }

        ProjectDocument project = projects.get(idx);

        System.out.print("Milestone title: ");
        String milestone = scanner.nextLine().trim();
        if (project.getMilestones().stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(milestone))) {
            System.out.println("Milestone already exists in this project.");
            return;
        }

        service.addMilestone(project.getName(), milestone);
        System.out.println("Milestone added.");
    }
}