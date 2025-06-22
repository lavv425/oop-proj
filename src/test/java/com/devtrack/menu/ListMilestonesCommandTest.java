package com.devtrack.menu;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.model.Task;
import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.utils.ConsoleStyle;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class ListMilestonesCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private ProjectServiceImpl projectService;
    private ListMilestonesCommand command;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        projectService = mock(ProjectServiceImpl.class);
        command = new ListMilestonesCommand(projectService);
    }

    @Test
    void printsMilestonesForEachProject() {
        Milestone m1 = new Milestone("Design");
        m1.addTask(new Task("Sketch UI"));

        Milestone m2 = new Milestone("Development");
        m2.addTask(new Task("Setup backend"));
        m2.addTask(new Task("Connect DB"));

        ProjectDocument project = new ProjectDocument("TestProject");
        project.addMilestone(m1);
        project.addMilestone(m2);

        when(projectService.listProjects()).thenReturn(List.of(project));

        command.execute(new Scanner(System.in));

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Project: TestProject"));
        Assertions.assertTrue(output.contains(" - Design (1 tasks)"));
        Assertions.assertTrue(output.contains(" - Development (2 tasks)"));
    }

    @Test
    void printsMessageIfNoProjects() {
        when(projectService.listProjects()).thenReturn(List.of());

        command.execute(new Scanner(System.in));

        String output = outContent.toString();
        Assertions.assertTrue(output.contains(ConsoleStyle.RED + "\nNo project available." + ConsoleStyle.RESET));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}