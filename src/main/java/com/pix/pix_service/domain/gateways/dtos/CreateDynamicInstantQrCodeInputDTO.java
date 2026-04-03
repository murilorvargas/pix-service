package com.pix.pix_service.domain.gateways.dtos;

import java.math.BigDecimal;

public record CreateDynamicInstantQrCodeInputDTO(
    String correlationId,
    QrCodePayerInputDTO qrCodePayer,
    BigDecimal amount,
    String description,
    Integer expiration
) {}
