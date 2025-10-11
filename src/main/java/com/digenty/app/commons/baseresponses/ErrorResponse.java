package com.digenty.app.commons.baseresponses;

public record ErrorResponse(
        Object message,
        String status,
        int errorCode) {
}
