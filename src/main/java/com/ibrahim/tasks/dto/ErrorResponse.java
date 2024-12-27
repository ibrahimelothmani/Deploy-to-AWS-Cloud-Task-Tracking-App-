package com.ibrahim.tasks.dto;

public record ErrorResponse(
        int status,
        String details,
        String message
) {
}
