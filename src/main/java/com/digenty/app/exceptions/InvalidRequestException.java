package com.digenty.app.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends BaseApiException {

    public InvalidRequestException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), "INVALID_REQUEST", message);
    }

    public InvalidRequestException(String message, Object details) {
        super(HttpStatus.BAD_REQUEST.value(), "INVALID_REQUEST", message, details);
    }
}
