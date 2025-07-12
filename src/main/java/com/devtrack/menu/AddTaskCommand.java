package com.devtrack.menu;

import com.devtrack.interfaces.MenuCommand;
import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Represents a command to add a task to a milestone within a project.
 * This command allows users to select a project, choose a milestone, and add a new task.
 * It interacts with the project service to perform the task addition.
 */
public class AddTaskCommand implements MenuCommand {
    private final ProjectServiceImpl projectService;

    public AddTaskCommand(ProjectServiceImpl projectService) {
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
        if (pIndex < 0 || pIndex >= projects.size())
            return;

        ProjectDocument selectedProject = projects.get(pIndex);
        List<Milestone> milestones = selectedProject.getMilestones();
        if (milestones.isEmpty()) {
            System.out.println("\nNo milestone available.");
            return;
        }

        System.out.println("\nChoose a milestone:");
        for (int i = 0; i < milestones.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, milestones.get(i).getTitle());
        }

        System.out.print("Milestone number: ");
        int mIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (mIndex < 0 || mIndex >= milestones.size())
            return;

        Milestone selectedMilestone = milestones.get(mIndex);

        System.out.print("Task title: ");
        String taskTitle = scanner.nextLine().trim();
        if (selectedMilestone.getTasks().stream().anyMatch(t -> t.getDescription().equalsIgnoreCase(taskTitle))) {
            System.out.println("Task with this title already exists in the milestone.");
            return;
        }

        ProjectDocument updated = projectService.addTask(
                selectedProject.getName(),
                selectedMilestone.getTitle(),
                taskTitle);

        System.out.println(updated != null ? "Task added." : "Error adding task.");
    }
}