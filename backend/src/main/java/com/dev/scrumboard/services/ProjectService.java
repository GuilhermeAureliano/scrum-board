package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;

public interface ProjectService {

    Project create(Project project) throws ApiException;
}
