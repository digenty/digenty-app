package com.digenty.app.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateRecordException extends BaseApiException {

    public DuplicateRecordException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_RECORD_REQUEST", message);
    }

    public DuplicateRecordException(String message, Object details) {
        super(HttpStatus.BAD_REQUEST.value(), "INVALID_REQUEST", message, details);
    }
}
