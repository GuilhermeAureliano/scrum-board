package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;

import java.util.List;

public interface UserService {

    User create(User user) throws ApiException;

    User edit(User user) throws ApiException;

    User getById(Long id) throws ApiException;

    List<User> getAll() throws ApiException;

    List<User> searchByTerm(String search) throws ApiException;
}
