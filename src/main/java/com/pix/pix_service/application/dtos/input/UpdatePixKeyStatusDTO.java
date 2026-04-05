package com.pix.pix_service.application.dtos.input;

public record UpdatePixKeyStatusDTO(
    String externalKey,
    PixKeyStatusDTO pixKeyStatus
) {
}
