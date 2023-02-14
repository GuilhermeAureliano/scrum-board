package com.dev.scrumboard.services;

import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return this.userRepository.save(user);
    }
}
