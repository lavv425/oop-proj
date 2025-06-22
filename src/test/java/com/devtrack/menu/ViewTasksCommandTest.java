package com.devtrack.menu;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.model.Task;
import com.devtrack.service.ProjectServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ViewTasksCommandTest {

    @Mock
    private ProjectServiceImpl projectService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void showsNoProjectsFound() {
        when(projectService.listProjects()).thenReturn(List.of());

        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        new ViewTasksCommand(projectService).execute(scanner);

        assertTrue(outContent.toString().contains("No projects found."));
    }

    @Test
    void displaysTasksCorrectly() {
        Task t1 = new Task("Setup DB");
        Task t2 = new Task("Build UI");
        t2.setDone();

        Milestone milestone = new Milestone("M1");
        milestone.addTask(t1);
        milestone.addTask(t2);

        ProjectDocument project = new ProjectDocument("MyProject");
        project.addMilestone(milestone);

        when(projectService.listProjects()).thenReturn(List.of(project));

        // Input: project index = 1
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        new ViewTasksCommand(projectService).execute(scanner);

        String output = outContent.toString();

        assertTrue(output.contains("Milestone: M1"));
        assertTrue(output.contains("[ ] Setup DB"));
        assertTrue(output.contains("[X] Build UI"));
    }

    @Test
    void handlesInvalidProjectIndex() {
        ProjectDocument project = new ProjectDocument("MyProject");
        when(projectService.listProjects()).thenReturn(List.of(project));

        // Invalid index 9
        Scanner scanner = new Scanner(new ByteArrayInputStream("9\n".getBytes()));

        new ViewTasksCommand(projectService).execute(scanner);

        String output = outContent.toString();
        assertTrue(output.contains("Choose a project:"));
    }

    @Test
    void showsMessageIfMilestoneHasNoTasks() {
        Milestone emptyMilestone = new Milestone("Empty Sprint");
        ProjectDocument project = new ProjectDocument("Proj");
        project.addMilestone(emptyMilestone);

        when(projectService.listProjects()).thenReturn(List.of(project));

        Scanner scanner = new Scanner(new ByteArrayInputStream("1\n".getBytes()));
        new ViewTasksCommand(projectService).execute(scanner);

        String output = outContent.toString();
        assertTrue(output.contains(" - No tasks"));
    }
}