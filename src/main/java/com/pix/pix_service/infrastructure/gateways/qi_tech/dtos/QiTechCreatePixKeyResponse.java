package com.pix.pix_service.infrastructure.gateways.qi_tech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QiTechCreatePixKeyResponse(
    @JsonProperty("pix_key_request_key") String pixKeyRequestKey
) {}
