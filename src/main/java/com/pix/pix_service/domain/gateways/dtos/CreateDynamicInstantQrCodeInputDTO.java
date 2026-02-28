package com.pix.pix_service.domain.gateways.dtos;

import com.pix.pix_service.application.dtos.input.QrCodePayerDTO;

public record CreateDynamicInstantQrCodeInputDTO(
    String correlationId,
    QrCodePayerDTO qrCodePayer,
    Double amount,
    String description,
    Integer expiration
) {}
