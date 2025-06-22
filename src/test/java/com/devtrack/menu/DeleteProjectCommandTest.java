package com.devtrack.menu;

import com.devtrack.model.ProjectDocument;
import com.devtrack.service.ProjectServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class DeleteProjectCommandTest {

    @Mock
    private ProjectServiceImpl projectService;

    private DeleteProjectCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new DeleteProjectCommand(projectService);
    }

    @Test
    void deletesProjectSuccessfully() {
        ProjectDocument project = new ProjectDocument("MyProj");
        project.setId("abc123");

        when(projectService.listProjects()).thenReturn(List.of(project));
        when(projectService.deleteProjectById("abc123")).thenReturn(true);

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).deleteProjectById("abc123");
    }

    @Test
    void doesNotDeleteWhenNoProjectsExist() {
        when(projectService.listProjects()).thenReturn(List.of());

        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        command.execute(scanner);

        verify(projectService).listProjects();
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void doesNotDeleteWhenInputIsInvalid() {
        ProjectDocument project = new ProjectDocument("MyProj");
        project.setId("abc123");

        when(projectService.listProjects()).thenReturn(List.of(project));

        String input = "invalid\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).listProjects();
        verify(projectService, never()).deleteProjectById(anyString());
    }

    @Test
    void doesNotDeleteWhenIndexOutOfRange() {
        ProjectDocument project = new ProjectDocument("MyProj");
        project.setId("abc123");

        when(projectService.listProjects()).thenReturn(List.of(project));

        String input = "3\n"; // Out of range
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).listProjects();
        verify(projectService, never()).deleteProjectById(anyString());
    }

    @Test
    void showsErrorIfDeleteFails() {
        ProjectDocument project = new ProjectDocument("MyProj");
        project.setId("abc123");

        when(projectService.listProjects()).thenReturn(List.of(project));
        when(projectService.deleteProjectById("abc123")).thenReturn(false);

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        command.execute(scanner);

        verify(projectService).deleteProjectById("abc123");
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }
}