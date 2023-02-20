package com.dev.scrumboard.dtos;

import com.dev.scrumboard.models.Project;
import lombok.Getter;

@Getter
public class ProjectResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String partnerInstitution;
    private final String scrumMasterName;

    public ProjectResponseDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.partnerInstitution = project.getPartnerInstitution();
        this.scrumMasterName = project.getResponsibleScrumMaster().getName();
    }
}
