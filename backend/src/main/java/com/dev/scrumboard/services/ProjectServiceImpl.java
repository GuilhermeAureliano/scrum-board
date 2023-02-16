package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project create(Project project) throws ApiException {
        return this.projectRepository.save(project);
    }
}
