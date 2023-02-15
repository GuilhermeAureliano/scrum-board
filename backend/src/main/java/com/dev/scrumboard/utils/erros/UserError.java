package com.dev.scrumboard.utils.erros;

import com.dev.scrumboard.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class UserError {

    static final String USER_ALREADY_EXIST = "Esse usuário já está cadastrado! Verifique o seu username ou email";
    static final String USER_NOT_EXIST = "Esse usuário não está cadastrado!";
    static final String TERM_NOT_FUND = "Nenhum usuário encontrado para o termo de busca fornecido!";
    static final String DUPLICATE_USERNAME_EDIT = "Não foi possível editar o usuário, username já existe!";
    static final String DUPLICATE_EMAIL_EDIT = "Não foi possível editar o usuário, email já existe!";

    public static ApiException erroUserAlreadyExist() {
        return new ApiException(UserError.USER_ALREADY_EXIST, HttpStatus.CONFLICT);
    }

    public static ApiException erroUserNotExist() {
        return new ApiException(UserError.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
    }

    public static ApiException erroUserTermNotFound() {
        return new ApiException(UserError.TERM_NOT_FUND, HttpStatus.NOT_FOUND);
    }

    public static ApiException erroDuplicateUserNameEdit() {
        return new ApiException(UserError.DUPLICATE_USERNAME_EDIT, HttpStatus.CONFLICT);
    }

    public static ApiException erroDuplicateEmailEdit() {
        return new ApiException(UserError.DUPLICATE_EMAIL_EDIT, HttpStatus.CONFLICT);
    }
}
