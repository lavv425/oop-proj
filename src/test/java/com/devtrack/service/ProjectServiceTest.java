package com.devtrack.service;

import com.devtrack.model.Milestone;
import com.devtrack.model.ProjectDocument;
import com.devtrack.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProject_savesAndReturnsProject() {
        String name = "TestProject";
        ProjectDocument saved = new ProjectDocument(name);
        when(projectRepository.save(any(ProjectDocument.class))).thenReturn(saved);

        ProjectDocument result = projectService.createProject(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(projectRepository).save(any(ProjectDocument.class));
    }

    @Test
    void listProjects_returnsAllProjects() {
        List<ProjectDocument> mockProjects = List.of(
            new ProjectDocument("Test All"),
            new ProjectDocument("Test All2")
        );
        when(projectRepository.findAll()).thenReturn(mockProjects);

        List<ProjectDocument> result = projectService.listProjects();

        assertEquals(2, result.size());
        assertEquals("Test All", result.get(0).getName());
    }

    @Test
    void deleteProjectByName_whenFound_deletesAndReturnsTrue() {
        ProjectDocument project = new ProjectDocument("Test To Delete");
        when(projectRepository.findByName("Test To Delete")).thenReturn(project);

        boolean deleted = projectService.deleteProjectByName("Test To Delete");

        assertTrue(deleted);
        verify(projectRepository).delete(project);
    }

    @Test
    void deleteProjectByName_whenNotFound_returnsFalse() {
        when(projectRepository.findByName("Test Not found")).thenReturn(null);

        boolean deleted = projectService.deleteProjectByName("Test Not found");

        assertFalse(deleted);
        verify(projectRepository, never()).delete(any());
    }

    @Test
    void deleteProjectById_whenExists_deletesAndReturnsTrue() {
        when(projectRepository.existsById("Test_id123")).thenReturn(true);

        boolean result = projectService.deleteProjectById("Test_id123");

        assertTrue(result);
        verify(projectRepository).deleteById("Test_id123");
    }

    @Test
    void deleteProjectById_whenNotExists_returnsFalse() {
        when(projectRepository.existsById("TEST_id999")).thenReturn(false);

        boolean result = projectService.deleteProjectById("TEST_id999");

        assertFalse(result);
        verify(projectRepository, never()).deleteById(any());
    }

    @Test
    void addMilestone_whenProjectFound_addsAndSaves() {
        ProjectDocument project = new ProjectDocument("Test Proj");
        when(projectRepository.findByName("Test Proj")).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);

        ProjectDocument result = projectService.addMilestone("Test Proj", "Test Milestone 1");

        assertNotNull(result);
        assertEquals(1, result.getMilestones().size());
        assertEquals("Test Milestone 1", result.getMilestones().get(0).getTitle());
    }

    @Test
    void addMilestone_whenProjectNotFound_returnsNull() {
        when(projectRepository.findByName("Test Missing Proj")).thenReturn(null);

        ProjectDocument result = projectService.addMilestone("Test Missing Proj", "TestM");

        assertNull(result);
    }

    @Test
    void addTask_whenMilestoneExists_addsTask() {
        Milestone milestone = new Milestone("TestM");
        ProjectDocument project = new ProjectDocument("Test Proj");
        project.addMilestone(milestone);

        when(projectRepository.findByName("Test Proj")).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);

        ProjectDocument result = projectService.addTask("Test Proj", "TestM", "New Task Test");

        assertNotNull(result);
        assertEquals(1, result.getMilestoneByTitle("TestM").getTasks().size());
        assertEquals("New Task Test", result.getMilestoneByTitle("TestM").getTasks().get(0).getDescription());
    }

    @Test
    void addTask_whenProjectNotFound_returnsNull() {
        when(projectRepository.findByName("Test Not Found")).thenReturn(null);

        ProjectDocument result = projectService.addTask("Test Not Found", "TestM", "Test Task");

        assertNull(result);
    }

    @Test
    void addTask_whenMilestoneNotFound_returnsNull() {
        ProjectDocument project = new ProjectDocument("Test Proj");
        when(projectRepository.findByName("Test Proj")).thenReturn(project);

        ProjectDocument result = projectService.addTask("Test Proj", "TestMNO", "Test Task");

        assertNull(result);
    }
}