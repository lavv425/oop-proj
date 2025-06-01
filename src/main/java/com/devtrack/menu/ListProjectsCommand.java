package com.devtrack.menu;

import com.devtrack.service.ProjectServiceImpl;

public class ListProjectsCommand implements Command {
    private final ProjectServiceImpl service;

    public ListProjectsCommand(ProjectServiceImpl service) {
        this.service = service;
    }

    @Override
    public void execute() {
        service.listProjects().forEach(
                p -> System.out.println("- " + p.getName() + " (" + p.getMilestones().size() + " milestones)"));
    }
}