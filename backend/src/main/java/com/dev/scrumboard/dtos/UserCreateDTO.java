package com.dev.scrumboard.dtos;

import com.dev.scrumboard.models.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class UserCreateDTO {

    private String name;
    private String userName;
    private String email;

    public User getModel() {

        return new User(
                null,
                this.name,
                this.userName,
                this.email,
                null);
    }
}
