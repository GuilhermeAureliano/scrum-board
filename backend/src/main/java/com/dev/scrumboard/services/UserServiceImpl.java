package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import com.dev.scrumboard.utils.erros.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) throws ApiException {

        Optional<User> userExist = this.userRepository.findByUserName(user.getUserName());
        if (userExist.isPresent()) {
            throw UserError.erroUserAlreadyExist();
        }

        userExist = this.userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw UserError.erroUserAlreadyExist();
        }

        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) throws ApiException {
        return null;
    }

    @Override
    public User getById(Long id) throws ApiException {
        Optional<User> userOpt = this.userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw UserError.erroUserNotExist();
        }

        return userOpt.get();
    }
}
