package com.dev.scrumboard.dtos;

import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.models.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String userName;
    private final String email;
    private final List<String> scrumMasterProjectsNames;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        Set<Project> projects = user.getScrumMasterProjects();
        if (projects == null) {
            this.scrumMasterProjectsNames = new ArrayList<>();;
        } else {
            List<String> projectNames = new ArrayList<>();
            for (Project project : projects) {
                projectNames.add(project.getName());
            }
            this.scrumMasterProjectsNames = projectNames;
        }

    }
}