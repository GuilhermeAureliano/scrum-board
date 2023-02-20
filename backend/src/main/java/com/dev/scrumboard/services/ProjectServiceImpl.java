package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.ProjectEditDTO;
import com.dev.scrumboard.dtos.ProjectResponseDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.repositories.ProjectRepository;
import com.dev.scrumboard.utils.erros.ProjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project create(Project project) throws ApiException {

        this.checkIfProjectExistByName(project, true);

        return this.projectRepository.save(project);
    }

    private void checkIfProjectExistByName(Project project, boolean isRegister) throws ApiException {
        Optional<Project> projectOpt = this.projectRepository.findByName(project.getName());

        if (projectOpt.isPresent() && isRegister) {
            throw ProjectError.erroProjectAlreadyExist(project.getName());
        } else if (projectOpt.isPresent() && !isRegister) {
            throw ProjectError.erroDuplicateProjectNameEdit();
        }

    }

    @Override
    public Project edit(Long id, ProjectEditDTO projectEditDTO) throws ApiException {
        Project project = this.getById(id);

        if (projectEditDTO.getName() != null && !projectEditDTO.getName().isBlank()) {
            project.setName(projectEditDTO.getName());
            this.checkIfProjectExistByName(project, false);
        }
        if (projectEditDTO.getDescription() != null && !projectEditDTO.getDescription().isBlank()) {
            project.setDescription(projectEditDTO.getDescription());
        }
        if (projectEditDTO.getPartnerInstitution() != null && !projectEditDTO.getPartnerInstitution().isBlank()) {
            project.setPartnerInstitution(projectEditDTO.getPartnerInstitution());
        }

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

    @Override
    public void delete(Long id) throws ApiException {
        Project project = this.getById(id);

        this.projectRepository.delete(project);
    }

    @Override
    public List<Project> getAll() throws ApiException {
        return this.projectRepository.findAll();
    }

    @Override
    public List<Project> searchByNome(String name) throws ApiException {
        List<Project> projectList = this.getAll();
        List<Project> matchingProjects = new ArrayList<>();

        String searchLowerCase = name.toLowerCase();
        for (Project project : projectList) {
            String nameLowerCase = project.getName().toLowerCase();

            if (nameLowerCase.contains(searchLowerCase)) {
                matchingProjects.add(project);
            }
        }

        if (matchingProjects.isEmpty()) {
            throw ProjectError.erroNameNotFound();
        }

        return matchingProjects;
    }
}
