package com.pix.pix_service.infrastructure.gateways.qi_tech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QiTechCreateQrCodeResponse(
    @JsonProperty("qr_code_key") String qrCodeKey,
    @JsonProperty("base_64") String base64
) {}
