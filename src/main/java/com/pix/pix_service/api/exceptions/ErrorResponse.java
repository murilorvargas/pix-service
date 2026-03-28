package com.pix.pix_service.api.exceptions;

public record ErrorResponse(
    String title,
    String message,
    String code
) {
}
