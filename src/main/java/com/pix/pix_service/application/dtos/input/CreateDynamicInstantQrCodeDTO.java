package com.pix.pix_service.application.dtos.input;

public record CreateDynamicInstantQrCodeDTO(
    String correlationId,
    QrCodePayerDTO qrCodePayer,
    Double amount,
    String description,
    Integer expiration
) {}
