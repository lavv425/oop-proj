package com.devtrack.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Milestone> milestones = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public void addMilestone(Milestone m) {
        milestones.add(m);
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public String getName() {
        return name;
    }
}