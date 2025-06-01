package com.devtrack.menu;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.model.Task;
import com.devtrack.service.ProjectServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ViewTasksCommand implements MenuCommand {
    private final ProjectServiceImpl projectService;

    public ViewTasksCommand(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @Override
    public void execute(Scanner scanner) {
        List<ProjectDocument> projects = projectService.listProjects();
        if (projects.isEmpty()) {
            System.out.println("\nNo projects found.");
            return;
        }

        System.out.println("\nChoose a project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, projects.get(i).getName());
        }

        System.out.print("Project number: ");
        int pIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (pIndex < 0 || pIndex >= projects.size()) return;

        List<Milestone> milestones = projects.get(pIndex).getMilestones();
        for (Milestone m : milestones) {
            System.out.println("\nMilestone: " + m.getTitle());
            if (m.getTasks().isEmpty()) {
                System.out.println(" - No tasks");
            } else {
                for (Task t : m.getTasks()) {
                    System.out.println(" - [" + (t.isDone() ? "X" : " ") + "] " + t.getDescription());
                }
            }
        }
    }
}
