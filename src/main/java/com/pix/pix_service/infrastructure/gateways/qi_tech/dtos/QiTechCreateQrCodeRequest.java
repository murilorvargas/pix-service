package com.pix.pix_service.infrastructure.gateways.qi_tech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QiTechCreateQrCodeRequest(
        @JsonProperty("account_key") String accountKey,
        @JsonProperty("pix_key") String pixKey,
        Double amount,
        @JsonProperty("expiration_seconds") Integer expirationSeconds,
        @JsonProperty("occurrence_type") String occurrenceType,
        @JsonProperty("payer_document_number") String payerDocumentNumber,
        @JsonProperty("payer_name") String payerName,
        @JsonProperty("payer_person_type") String payerPersonType,
        @JsonProperty("payer_request") String payerRequest,
        @JsonProperty("receiver_conciliation_id") String receiverConciliationId,
        @JsonProperty("qr_code_type") String qrCodeType
) {}
