package com.dev.scrumboard.controllers;

import com.dev.scrumboard.dtos.UserCreateDTO;
import com.dev.scrumboard.dtos.UserResponseDTO;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam String name, @RequestParam String userName, @RequestParam String email) {
        UserCreateDTO userDTO = new UserCreateDTO(name, userName, email);

        User createdUser = this.userService.create(userDTO.getModel());
        UserResponseDTO response = new UserResponseDTO(createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
