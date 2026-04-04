package com.pix.pix_service.infrastructure.gateways.qi_tech.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QiTechCreatePixKeyRequest(
    @JsonProperty("account_key") String accountKey,
    @JsonProperty("pix_key") String pixKey,
    @JsonProperty("pix_key_type") String pixKeyType
) {}
