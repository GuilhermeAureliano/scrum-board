package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.ProjectEditDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;
import io.swagger.annotations.Api;

public interface ProjectService {

    Project create(Project project) throws ApiException;

    Project edit(Long id, ProjectEditDTO projectEditDTO) throws ApiException;

    Project getById(Long id) throws ApiException;

    void delete(Long id) throws ApiException;
}
