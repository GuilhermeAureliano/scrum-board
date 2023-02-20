package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.UserCreateDTO;
import com.dev.scrumboard.dtos.UserEditDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @Test
    public void create() throws ApiException {
        UserCreateDTO userCreateDTO1 = new UserCreateDTO("Gabriel Barbosa", "gabigol", "gabi@email.com");
        User user1 = this.userService.create(userCreateDTO1.getModel());
        assertTrue(this.userRepository.findById(user1.getId()).isPresent());

        UserCreateDTO userCreateDTO2 = new UserCreateDTO("Fulano", "shelby", "fulano@email.com");
        try {
            this.userService.create(userCreateDTO2.getModel());
        } catch (ApiException e) {
            assertEquals("Esse usuário já está cadastrado! Verifique o seu username ou email", e.getMessage());
        }

    }

    @BeforeEach
    @Test
    public void edit() throws ApiException {
        UserEditDTO userEditDTO1 = new UserEditDTO("Tommas Shelby", "", "contatoshelby@gmail.com");

        User user1 = this.userService.edit(1L, userEditDTO1);
        assertEquals("Tommas Shelby", user1.getName());
        assertEquals("shelby", user1.getUserName());
        assertEquals("contatoshelby@gmail.com", user1.getEmail());

        UserEditDTO userEditDTO2 = new UserEditDTO("Luis Alberto", "shelby", "");
        try {
            User user2 = this.userService.edit(5L, userEditDTO2);
        } catch (ApiException e) {
            assertEquals("Não foi possível editar o usuário, username já existe!", e.getMessage());
        }
    }

    @BeforeEach
    @Test
    public void getById() throws ApiException {
        User user = this.userService.getById(1L);
        assertEquals("1", String.valueOf(user.getId()));
        assertEquals("shelby", user.getUserName());

        try {
            User userError = this.userService.getById(10L);
        } catch (ApiException e) {
            assertEquals("Esse usuário não está cadastrado!", e.getMessage());
        }
    }

    @BeforeEach
    @Test
    public void delete() throws ApiException {
        this.userService.delete(2L);
        Optional<User> userOpt = this.userRepository.findById(2L);

        assertFalse(userOpt.isPresent());

        try {
            this.userService.delete(15L);
        } catch (ApiException e) {
            assertEquals("Esse usuário não está cadastrado!", e.getMessage());
        }
    }

    @BeforeEach
    @Test
    public void getAll() throws ApiException {
        List<User> userList = new ArrayList<>();
        userList.add(this.userService.getById(1L));
        userList.add(this.userService.getById(3L));
        userList.add(this.userService.getById(4L));
        userList.add(this.userService.getById(5L));
        userList.add(this.userService.getById(6L));

        List<User> result = this.userService.getAll();
        assertEquals(userList, result);

    }

    @BeforeEach
    @Test
    public void searchByTerm() throws ApiException {
        List<User> userList = new ArrayList<>();
        userList.add(this.userService.getById(1L));
        userList.add(this.userService.getById(3L));
        userList.add(this.userService.getById(5L));

        List<User> result = this.userService.searchByTerm("ShELby");
        assertEquals(userList, result);

       result = this.userService.searchByTerm("lUIs");
       assertNotEquals(userList, result);

    }
}
