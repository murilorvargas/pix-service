package com.pix.pix_service.application.dtos.input;

import java.math.BigDecimal;

public record CreateDynamicInstantQrCodeDTO(
    String correlationId,
    QrCodePayerDTO qrCodePayer,
    BigDecimal amount,
    String description,
    Integer expiration
) {}
