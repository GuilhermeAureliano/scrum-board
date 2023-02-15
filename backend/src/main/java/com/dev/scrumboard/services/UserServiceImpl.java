package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.UserEditDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import com.dev.scrumboard.utils.erros.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) throws ApiException {

        this.checkIfUserExistByUserName(user, true);
        this.checkIfUserExistByUserEmail(user, true);

        return this.userRepository.save(user);
    }

    private void checkIfUserExistByUserName(User user, boolean isRegister) throws ApiException {
        Optional<User> userExist = this.userRepository.findByUserName(user.getUserName());
        if (userExist.isPresent() && isRegister) {
            throw UserError.erroUserAlreadyExist();

        } else if (userExist.isPresent() && !isRegister) {
            throw UserError.erroDuplicateUserNameEdit();
        }
    }

    private void checkIfUserExistByUserEmail(User user, boolean isRegister) throws ApiException {
        Optional<User> userExist = this.userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent() && isRegister) {
            throw UserError.erroUserAlreadyExist();
        } else if(userExist.isPresent() && !isRegister) {
            throw UserError.erroDuplicateEmailEdit();
        }
    }

    @Override
    public User edit(Long id, UserEditDTO userEditDTO) throws ApiException {
        User user = this.getById(id);

        if (userEditDTO.getName() != null && !userEditDTO.getName().isBlank()) {
            user.setName(userEditDTO.getName());
        }
        if (userEditDTO.getUserName() != null && !userEditDTO.getUserName().isBlank()) {
            user.setUserName(userEditDTO.getUserName());
            this.checkIfUserExistByUserName(user, false);
        }
        if (userEditDTO.getEmail() != null & !userEditDTO.getEmail().isBlank()) {
            user.setEmail(userEditDTO.getEmail());
            this.checkIfUserExistByUserEmail(user, false);
        }

        return this.userRepository.save(user);
    }

    @Override
    public User getById(Long id) throws ApiException {
        Optional<User> userOpt = this.userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw UserError.erroUserNotExist();
        }

        return userOpt.get();
    }

    @Override
    public void delete(Long id) throws ApiException {
        User user = this.getById(id);

        this.userRepository.delete(user);
    }

    @Override
    public List<User> getAll() throws ApiException {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> searchByTerm(String search) throws ApiException {
        List<User> userList = this.getAll();
        List<User> matchingUsers = new ArrayList<>();

        String termLowerCase = search.toLowerCase();
        for (User user : userList) {
            String nameLowerCase = user.getName().toLowerCase();
            String userNameLowerCase = user.getUserName().toLowerCase();

            if (nameLowerCase.contains(termLowerCase) || userNameLowerCase.contains(termLowerCase)) {
                matchingUsers.add(user);
            }
        }

        if (matchingUsers.isEmpty()) {
            throw UserError.erroUserTermNotFound();
        }

        return matchingUsers;
    }
}
