package com.devtrack.service;

import com.devtrack.interfaces.ProjectCommandService;
import com.devtrack.interfaces.ProjectService;
import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.model.Task;
import com.devtrack.repository.ProjectRepository;
import com.devtrack.security.ExceptionShield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService, ProjectCommandService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDocument createProject(String name) {
        try {
            ProjectDocument project = new ProjectDocument(name);
            return projectRepository.save(project);
        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.err.println("Failed to create a project: " + e.getMessage());
            return null;
        }
    }

    public List<ProjectDocument> listProjects() {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.err.println("Failed to list projects: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteProjectByName(String name) {
        try {
            ProjectDocument found = projectRepository.findByName(name);
            if (found != null) {
                projectRepository.delete(found);
                return true;
            }
            return false;
        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.err.println("Failed to delete the project '" + name + "': " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProjectById(String id) {
        try {
            if (projectRepository.existsById(id)) {
                projectRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.err.println("Error deleting project: " + e.getMessage());
            return false;
        }
    }

    public ProjectDocument addMilestone(String projectName, String milestoneTitle) {
        try {
            ProjectDocument project = projectRepository.findByName(projectName);
            // System.out.println("Project found: " + project);
            if (project != null) {
                project.addMilestone(new Milestone(milestoneTitle));
                return projectRepository.save(project);
            }
            return null;
        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.err.println("Failed to add milestone: " + e.getMessage());
            return null;
        }
    }

    public ProjectDocument addTask(String projectName, String milestoneTitle, String taskTitle) {
        try {
            ProjectDocument project = projectRepository.findByName(projectName);
            if (project == null) {
                System.out.println("Project not found.");
                return null;
            }

            Milestone milestone = project.getMilestoneByTitle(milestoneTitle);
            if (milestone == null) {
                System.out.println("Milestone not found.");
                return null;
            }

            milestone.addTask(new Task(taskTitle));
            return projectRepository.save(project);

        } catch (Exception e) {
            ExceptionShield.handle(e);
            System.out.println("Failed to add task: " + e.getMessage());
            return null;
        }
    }
}