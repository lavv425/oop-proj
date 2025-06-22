package com.devtrack.menu;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.*;

class ListProjectsCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private ProjectServiceImpl projectService;
    private ListProjectsCommand command;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        projectService = mock(ProjectServiceImpl.class);
        command = new ListProjectsCommand(projectService);
    }

    @Test
    void printsAllProjectsWithMilestoneCount() {
        ProjectDocument p1 = new ProjectDocument("Alpha");
        p1.addMilestone(new Milestone("Start"));

        ProjectDocument p2 = new ProjectDocument("Beta");
        p2.addMilestone(new Milestone("Sprint 1"));
        p2.addMilestone(new Milestone("Sprint 2"));

        when(projectService.listProjects()).thenReturn(List.of(p1, p2));

        command.execute();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("- Alpha (1 milestones)"));
        Assertions.assertTrue(output.contains("- Beta (2 milestones)"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}