package com.dev.scrumboard.utils.erros;

import com.dev.scrumboard.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ProjectError {

    static final String PROJECT_NOT_EXIST = "Esse projeto não está cadastrado!";

    static String PROJECT_ALREADY_EXIST = "O projeto %s já está cadastrado!";

    static final String DUPLICATE_PROJECT_NAME_EDIT = "Não foi possível editar o projeto, o nome já existe!";

    public static ApiException erroProjectNotExist() {
        return new ApiException(ProjectError.PROJECT_NOT_EXIST, HttpStatus.NOT_FOUND);
    }

    public static ApiException erroProjectAlreadyExist(String name) {
        String errorMessage = String.format(PROJECT_ALREADY_EXIST, name);
        return new ApiException(errorMessage, HttpStatus.CONFLICT);
    }

    public static ApiException erroDuplicateProjectNameEdit() {
        return new ApiException(ProjectError.DUPLICATE_PROJECT_NAME_EDIT, HttpStatus.CONFLICT);
    }
}
