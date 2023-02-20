package com.dev.scrumboard.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ProjectEditDTO {

    private String name;
    private String description;
    private String partnerInstitution;
}
