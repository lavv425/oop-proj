package com.devtrack.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDocumentTest {

    @Test
    void constructor_shouldSetNameAndInitializeEmptyMilestones() {
        ProjectDocument doc = new ProjectDocument("Test Init Empty");

        assertEquals("Test Init Empty", doc.getName());
        assertNotNull(doc.getMilestones());
        assertTrue(doc.getMilestones().isEmpty());
    }

    @Test
    void addMilestone_shouldAddToMilestonesList() {
        ProjectDocument doc = new ProjectDocument("Test Add to miles list");
        Milestone m = new Milestone("Test milestone");

        doc.addMilestone(m);

        List<Milestone> milestones = doc.getMilestones();
        assertEquals(1, milestones.size());
        assertEquals("Test milestone", milestones.get(0).getTitle());
    }

    @Test
    void getMilestoneByTitle_shouldReturnCorrectMilestoneIgnoringCase() {
        ProjectDocument doc = new ProjectDocument("Test get ignore case");
        Milestone m1 = new Milestone("Setup");
        Milestone m2 = new Milestone("TesT igNore CaSe");

        doc.addMilestone(m1);
        doc.addMilestone(m2);

        Milestone found = doc.getMilestoneByTitle("Test ignore case");

        assertNotNull(found);
        assertEquals("TesT igNore CaSe", found.getTitle());
    }

    @Test
    void getMilestoneByTitle_shouldReturnNullIfNotFound() {
        ProjectDocument doc = new ProjectDocument("Test return null if not found");

        Milestone found = doc.getMilestoneByTitle("TESTNonExistent");

        assertNull(found);
    }

    @Test
    void getId_shouldReturnNullInitially() {
        ProjectDocument doc = new ProjectDocument("TestNoID");

        assertNull(doc.getId()); // MongoDB sets this only after persistence
    }
}