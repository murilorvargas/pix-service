package com.pix.pix_service.domain.gateways.dtos;

public record CreateDynamicInstantQrCodeOutputDTO(
        String externalKey,
        String brCode
) {}
