package com.devtrack.repository;

import com.devtrack.model.ProjectDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProjectRepository extends MongoRepository<ProjectDocument, String> {
    ProjectDocument findByName(String name);

    @Query("{ 'milestones.title': ?0 }")
    ProjectDocument findByMilestoneTitle(String milestoneTitle);
}