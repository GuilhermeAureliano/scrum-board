package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getById() throws ApiException {
        User user = this.userService.getById(1L);
        assertEquals("1", String.valueOf(user.getId()));
        assertEquals("shelby", user.getUserName());
    }

    @Test
    public void delete() throws ApiException {
        this.userService.delete(2L);
        Optional<User> userOpt = this.userRepository.findById(2L);

        assertFalse(userOpt.isPresent());
    }
}
