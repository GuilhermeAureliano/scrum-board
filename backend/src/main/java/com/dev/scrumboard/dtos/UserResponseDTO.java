package com.dev.scrumboard.dtos;

import com.dev.scrumboard.models.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String userName;
    private final String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}