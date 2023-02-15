package com.dev.scrumboard.controllers;

import com.dev.scrumboard.dtos.UserCreateDTO;
import com.dev.scrumboard.dtos.UserEditDTO;
import com.dev.scrumboard.dtos.UserResponseDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam String name, @RequestParam String userName, @RequestParam String email) throws ApiException {
        UserCreateDTO userDTO = new UserCreateDTO(name, userName, email);

        User createdUser = this.userService.create(userDTO.getModel());
        UserResponseDTO response = new UserResponseDTO(createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody UserEditDTO userEditDTO) throws ApiException {
        User user = this.userService.edit(id, userEditDTO);
        UserResponseDTO response = new UserResponseDTO(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable("id") Long id) throws ApiException {
        User user = this.userService.getById(id);
        UserResponseDTO response = new UserResponseDTO(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ApiException {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws ApiException {
        List<User> userList = this.userService.getAll();
        List<UserResponseDTO> responseList = userList.stream().map(UserResponseDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchByTerm(@RequestParam String term) throws ApiException {
        List<User> userList = this.userService.searchByTerm(term);
        List<UserResponseDTO> responseList = userList.stream().map(UserResponseDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
