package com.dev.scrumboard.services;

import com.dev.scrumboard.dtos.UserEditDTO;
import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;
import io.swagger.annotations.Api;

import java.util.List;

public interface UserService {

    User create(User user) throws ApiException;

    User edit(Long id, UserEditDTO userEditDTO) throws ApiException;

    User getById(Long id) throws ApiException;

    void delete(Long id) throws ApiException;

    List<User> getAll() throws ApiException;

    List<User> searchByTerm(String search) throws ApiException;
}
