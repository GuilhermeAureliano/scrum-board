package com.dev.scrumboard.repositories;

import com.dev.scrumboard.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
