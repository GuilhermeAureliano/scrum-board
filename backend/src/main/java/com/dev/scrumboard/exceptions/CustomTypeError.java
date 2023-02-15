package com.dev.scrumboard.exceptions;

import org.springframework.http.HttpStatus;

public class CustomTypeError {
    private String errorMessage;
    private HttpStatus statusCode;

    public CustomTypeError(String errorMessage, HttpStatus statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public void CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}