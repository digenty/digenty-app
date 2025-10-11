package com.digenty.app.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseApiException {

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND", message);
    }

    public ResourceNotFoundException(String message, Object details) {
        super(HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND", message, details);
    }
}
