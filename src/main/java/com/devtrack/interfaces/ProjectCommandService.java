package com.devtrack.interfaces;

import com.devtrack.model.ProjectDocument;
import java.util.List;

public interface ProjectCommandService {
    List<ProjectDocument> listProjects();

    ProjectDocument addMilestone(String projectName, String milestoneTitle);
}