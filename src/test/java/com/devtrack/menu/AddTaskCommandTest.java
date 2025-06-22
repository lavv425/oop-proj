package com.devtrack.menu;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.model.Task;
import com.devtrack.service.ProjectServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class AddTaskCommandTest {

    @Mock
    private ProjectServiceImpl projectService;

    private AddTaskCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new AddTaskCommand(projectService);
    }

    @Test
    void addsTaskSuccessfully() {
        ProjectDocument mockProject = new ProjectDocument("TestP");
        Milestone milestoneForInput = new Milestone("Sprint 1");
        mockProject.addMilestone(milestoneForInput);

        when(projectService.listProjects()).thenReturn(List.of(mockProject));

        ProjectDocument updatedProject = new ProjectDocument("TestP");
        Milestone updatedMilestone = new Milestone("Sprint 1");
        updatedMilestone.addTask(new Task("Implement login"));
        updatedProject.addMilestone(updatedMilestone);

        when(projectService.addTask("TestP", "Sprint 1", "Implement login")).thenReturn(updatedProject);

        // Simulated user input
        String input = "1\n1\nImplement login\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).addTask("TestP", "Sprint 1", "Implement login");
    }

    @Test
    void showsMessageIfNoProjectsExist() {
        when(projectService.listProjects()).thenReturn(List.of());

        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        command.execute(scanner);

        verify(projectService).listProjects();
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void doesNotAddIfMilestoneEmpty() {
        ProjectDocument mockProject = new ProjectDocument("Empty Project");

        when(projectService.listProjects()).thenReturn(List.of(mockProject));

        // Simulated user input
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).listProjects();
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void abortsIfTaskAlreadyExists() {
        ProjectDocument project = new ProjectDocument("TestP");
        Milestone milestone = new Milestone("Sprint 1");
        milestone.addTask(new Task("Already exists"));
        project.addMilestone(milestone);

        when(projectService.listProjects()).thenReturn(List.of(project));

        String input = "1\n1\nAlready exists\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).listProjects();
        verifyNoMoreInteractions(projectService);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }
}