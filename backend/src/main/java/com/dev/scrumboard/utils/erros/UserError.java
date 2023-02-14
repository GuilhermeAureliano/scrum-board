package com.dev.scrumboard.utils.erros;

import com.dev.scrumboard.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class UserError {

    static final String USER_ALREADY_EXIST = "Esse usuário já está cadastrado! Verifique o seu username ou email";
    static final String USER_NOT_EXIST = "Esse usuário não está cadastrado!";

    public static ApiException erroUserAlreadyExist() {
        return new ApiException(UserError.USER_ALREADY_EXIST, HttpStatus.CONFLICT);
    }

    public static ApiException erroUserNotExist() {
        return new ApiException(UserError.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
    }
}
