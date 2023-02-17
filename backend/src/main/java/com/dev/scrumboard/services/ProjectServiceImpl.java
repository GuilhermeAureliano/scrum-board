package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.ProjectEditDTO;
import com.dev.scrumboard.dtos.ProjectResponseDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.repositories.ProjectRepository;
import com.dev.scrumboard.utils.erros.ProjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project create(Project project) throws ApiException {

        this.checkIfProjectExistByName(project);

        return this.projectRepository.save(project);
    }

    private void checkIfProjectExistByName(Project project) throws ApiException {
        Optional<Project> projectOpt = this.projectRepository.findByName(project.getName());

        if (projectOpt.isPresent()) {
            throw ProjectError.erroProjectAlreadyExist(project.getName());
        }

    }

    @Override
    public Project edit(Long id, ProjectEditDTO projectEditDTO) throws ApiException {
        Project project = this.getById(id);

        project.setName(projectEditDTO.getName());
        project.setDescription(projectEditDTO.getDescription());
        project.setPartnerInstitution(projectEditDTO.getPartnerInstitution());

        return this.projectRepository.save(project);
    }

    @Override
    public Project getById(Long id) throws ApiException {
        Optional<Project> projectOpt = this.projectRepository.findById(id);

        if (projectOpt.isEmpty()) {
            throw ProjectError.erroProjectNotExist();
        }

        return projectOpt.get();
    }
}
