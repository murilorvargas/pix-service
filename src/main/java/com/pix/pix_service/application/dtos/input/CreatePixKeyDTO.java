package com.pix.pix_service.application.dtos.input;

public record CreatePixKeyDTO(
    String pixKey,
    PixKeyTypeDTO pixKeyType
) {
}
