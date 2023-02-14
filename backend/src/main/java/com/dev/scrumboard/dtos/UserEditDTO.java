package com.dev.scrumboard.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class UserEditDTO {

    private String name;
    private String userName;
    private String email;


}
