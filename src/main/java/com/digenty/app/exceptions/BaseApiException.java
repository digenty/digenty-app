package com.digenty.app.exceptions;

import lombok.Data;

@Data
public abstract class BaseApiException extends RuntimeException{
    private final int statusCode;
    private final String errorCode;
    private final Object details;

    public BaseApiException(int statusCode, String errorCode, String message) {
        this(statusCode, errorCode, message, null);
    }

    public BaseApiException(int statusCode, String errorCode, String message, Object details) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.details = details;
    }
}
