package com.dev.scrumboard.controllers;

import com.dev.scrumboard.dtos.ProjectCreateDTO;
import com.dev.scrumboard.dtos.ProjectEditDTO;
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

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody ProjectEditDTO projectEditDTO) throws ApiException {
        Project project = this.projectService.edit(id, projectEditDTO);

        ProjectResponseDTO response = new ProjectResponseDTO(project);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable("id") Long id) throws ApiException {
        Project project = this.projectService.getById(id);
        ProjectResponseDTO response = new ProjectResponseDTO(project);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ApiException {
        this.projectService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws ApiException {
        List<Project> projectList = this.projectService.getAll();
        List<ProjectResponseDTO> responseList = projectList.stream().map(ProjectResponseDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
