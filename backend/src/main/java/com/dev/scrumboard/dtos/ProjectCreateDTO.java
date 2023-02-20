package com.dev.scrumboard.dtos;

import com.dev.scrumboard.models.Project;
import com.dev.scrumboard.models.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ProjectCreateDTO {

    private String name;

    private String description;

    private String partnerInstitution;

    @ApiModelProperty(value = "Identificador do Usuário para cadastrar como Scrum Master do Projeto", required = true)
    private Long scrumMasterID;

    public Project getModel() {

        return new Project(
                null,
                this.name,
                this.description,
                this.partnerInstitution,
                null);
    }
}
