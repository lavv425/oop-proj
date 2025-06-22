package com.devtrack.menu;

import com.devtrack.interfaces.ProjectService;
import com.devtrack.model.ProjectDocument;
import com.devtrack.security.ExceptionShield;
import com.devtrack.security.InputSanitizer;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateProjectCommandTest {

    private ProjectService projectService;
    private CreateProjectCommand command;
    private Scanner scanner;

    private static MockedStatic<InputSanitizer> sanitizerMock;
    private static MockedStatic<ExceptionShield> exceptionShieldMock;

    @BeforeEach
    void setUp() {
        projectService = mock(ProjectService.class);
        command = new CreateProjectCommand(projectService);

        // Mock static methods
        sanitizerMock = mockStatic(InputSanitizer.class);
        exceptionShieldMock = mockStatic(ExceptionShield.class);
    }

    
    @Test
    void createsProjectWithValidName() {
        String userInput = "MyProj\n";
        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        
        sanitizerMock.when(() -> InputSanitizer.sanitize("MyProj")).thenReturn("MyProj");
        
        when(projectService.createProject("MyProj")).thenReturn(new ProjectDocument("MyProj"));
        
        command.execute(scanner);
        
        verify(projectService).createProject("MyProj");
    }
    
    @Test
    void doesNotCreateProjectWhenNameIsEmptyAfterSanitization() {
        String userInput = "   \n";
        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        
        sanitizerMock.when(() -> InputSanitizer.sanitize("")).thenReturn("");
        
        command.execute(scanner);
        
        verify(projectService, never()).createProject(any());
    }
    
    @Test
    void handlesExceptionDuringExecution() {
        String userInput = "CrashMe\n";
        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        
        sanitizerMock.when(() -> InputSanitizer.sanitize("CrashMe")).thenReturn("CrashMe");
        when(projectService.createProject("CrashMe")).thenThrow(new RuntimeException("Boom"));
        
        command.execute(scanner);
        
        exceptionShieldMock.verify(() -> ExceptionShield.handle(any(RuntimeException.class)));
    }

    @AfterEach
    void tearDown() {
        sanitizerMock.close();
        exceptionShieldMock.close();
    }
}