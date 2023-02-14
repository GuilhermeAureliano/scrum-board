package com.dev.scrumboard.utils.erros;

import com.dev.scrumboard.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserError {

    static final String USER_ALREADY_EXIST = "Esse usuário já está cadastrado! Verifique o seu username ou email";

    public static ApiException erroUserAlreadyExist() {
        return new ApiException(UserError.USER_ALREADY_EXIST, HttpStatus.CONFLICT);
    }
}
