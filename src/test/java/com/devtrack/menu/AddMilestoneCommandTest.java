package com.devtrack.menu;

import com.devtrack.interfaces.ProjectCommandService;
import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class AddMilestoneCommandTest {

    private ProjectCommandService service;
    private AddMilestoneCommand command;

    @BeforeEach
    void setUp() {
        service = mock(ProjectCommandService.class);
        command = new AddMilestoneCommand(service);
    }

    @Test
    void addsMilestoneToSelectedProject() {
        ProjectDocument project = new ProjectDocument("Test Project");
        when(service.listProjects()).thenReturn(List.of(project));

        Scanner scanner = new Scanner("1\nTestM\n");
        command.execute(scanner);

        verify(service).addMilestone(eq("Test Project"), eq("TestM"));
    }

    @Test
    void doesNotAddIfProjectListIsEmpty() {
        when(service.listProjects()).thenReturn(new ArrayList<>());
        Scanner scanner = new Scanner("1\nTestM\n");

        command.execute(scanner);

        verify(service, never()).addMilestone(any(), any());
    }

    @Test
    void doesNotAddIfIndexIsInvalid() {
        List<ProjectDocument> projects = List.of(new ProjectDocument("Test Proj"));
        when(service.listProjects()).thenReturn(projects);

        Scanner scanner = new Scanner("5\nTest Title\n");
        command.execute(scanner);

        verify(service, never()).addMilestone(any(), any());
    }

    @Test
    void doesNotAddIfMilestoneAlreadyExists() {
        ProjectDocument project = new ProjectDocument("Test Proj");
        project.addMilestone(new Milestone("Test Exists"));
        when(service.listProjects()).thenReturn(List.of(project));

        Scanner scanner = new Scanner("1\nTest Exists\n");
        command.execute(scanner);

        verify(service, never()).addMilestone(any(), any());
    }
}
