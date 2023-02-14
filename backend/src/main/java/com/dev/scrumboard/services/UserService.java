package com.dev.scrumboard.services;

import com.dev.scrumboard.exceptions.ApiException;
import com.dev.scrumboard.models.User;

public interface UserService {

    User create(User user) throws ApiException;
}
