package com.digenty.app.commons.baseresponses;

public record TermiiStandardResponse(
        String status,
        Object data,
        String message
) {
}
