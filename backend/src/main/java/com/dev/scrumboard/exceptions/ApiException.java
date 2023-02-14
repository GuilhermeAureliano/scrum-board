package com.dev.scrumboard.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    private final CustomTypeError apiError;

    public ApiException(CustomTypeError apiError) {
        super(apiError.getErrorMessage());
        this.apiError = apiError;
    }

    public ApiException(String message, HttpStatus statusCode) {
        this(new CustomTypeError(message, statusCode));
    }

    public ApiException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus getStatusCode() {
        return apiError.getStatusCode();
    }

    public CustomTypeError getApiError() {
        return apiError;
    }
}