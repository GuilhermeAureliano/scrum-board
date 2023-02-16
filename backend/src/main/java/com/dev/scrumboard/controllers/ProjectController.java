package com.dev.scrumboard.controllers;

import com.dev.scrumboard.dtos.ProjectCreateDTO;
import com.dev.scrumboard.dtos.ProjectResponseDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.services.ProjectService;
import com.dev.scrumboard.services.ProjectServiceImpl;
import com.dev.scrumboard.services.UserService;
import com.dev.scrumboard.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProjectCreateDTO projectDTO) throws ApiException {

        User user = this.userService.getById(projectDTO.getScrumMasterID());

        Project createdProject = this.projectService.create(projectDTO.getModel());
        createdProject.setResponsibleScrumMaster(user);
        this.userService.addScrumMasterProject(user, createdProject);
        ProjectResponseDTO response = new ProjectResponseDTO(createdProject);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
