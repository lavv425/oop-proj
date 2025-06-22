package com.devtrack.interfaces;

import com.devtrack.model.ProjectDocument;

import java.util.List;

public interface ProjectService {
    ProjectDocument createProject(String name);
    List<ProjectDocument> listProjects();
    boolean deleteProjectByName(String name);
    ProjectDocument addMilestone(String projectName, String milestoneTitle);
    ProjectDocument addTask(String projectName, String milestoneTitle, String taskTitle);
    boolean deleteProjectById(String id);
}