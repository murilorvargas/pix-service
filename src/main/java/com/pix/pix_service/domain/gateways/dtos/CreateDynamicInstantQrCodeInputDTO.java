package com.pix.pix_service.domain.gateways.dtos;

public record CreateDynamicInstantQrCodeInputDTO(
    String correlationId,
    QrCodePayerInputDTO qrCodePayer,
    Double amount,
    String description,
    Integer expiration
) {}
