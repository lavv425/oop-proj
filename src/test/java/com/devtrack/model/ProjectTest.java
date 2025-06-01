package com.devtrack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testAddMilestone() {
        Project p = new Project("DevTrack");
        Milestone m = new Milestone("MVP");
        p.addMilestone(m);

        assertEquals(1, p.getMilestones().size());
        assertEquals("MVP", p.getMilestones().get(0).getTitle());
    }
}