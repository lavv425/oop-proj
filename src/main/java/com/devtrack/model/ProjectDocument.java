package com.devtrack.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("projects")
public class ProjectDocument {
    @Id
    private String id;
    private String name;
    private List<Milestone> milestones = new ArrayList<>();

    public ProjectDocument(String name) {
        this.name = name;
    }

    public void addMilestone(Milestone m) {
        milestones.add(m);
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public Milestone getMilestoneByTitle(String title) {
        Milestone foundMilestone = milestones.stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).findFirst()
                .orElse(null);

        return foundMilestone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}