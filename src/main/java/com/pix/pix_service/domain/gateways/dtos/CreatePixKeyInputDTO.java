package com.pix.pix_service.domain.gateways.dtos;

public record CreatePixKeyInputDTO(
    String pixKey,
    PixKeyTypeInputDTO pixKeyType
) {}
