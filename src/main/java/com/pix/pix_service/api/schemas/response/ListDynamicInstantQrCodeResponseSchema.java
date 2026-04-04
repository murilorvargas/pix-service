package com.pix.pix_service.api.schemas.response;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;

import java.math.BigDecimal;

public class ListDynamicInstantQrCodeResponseSchema {

    private String publicKey;
    private String correlationId;
    private BigDecimal amount;
    private String description;
    private Integer expiration;
    private String status;
    private QrCodePayerResponseSchema qrCodePayer;

    public ListDynamicInstantQrCodeResponseSchema() {
    }

    public ListDynamicInstantQrCodeResponseSchema(
            String publicKey,
            String correlationId,
            BigDecimal amount,
            String description,
            Integer expiration,
            String status,
            QrCodePayerResponseSchema qrCodePayer
    ) {
        this.publicKey = publicKey;
        this.correlationId = correlationId;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
        this.status = status;
        this.qrCodePayer = qrCodePayer;
    }

    public static ListDynamicInstantQrCodeResponseSchema fromEntity(DynamicInstantQrCode entity) {
        return new ListDynamicInstantQrCodeResponseSchema(
                entity.getPublicKey(),
                entity.getCorrelationId(),
                entity.getAmount(),
                entity.getDescription(),
                entity.getExpiration(),
                entity.getDynamicInstantQrCodeStatus() != null
                        ? entity.getDynamicInstantQrCodeStatus().getEnumerator()
                        : null,
                entity.getQrCodePayer() != null
                        ? QrCodePayerResponseSchema.fromEntity(entity.getQrCodePayer())
                        : null
        );
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public String getStatus() {
        return status;
    }

    public QrCodePayerResponseSchema getQrCodePayer() {
        return qrCodePayer;
    }
}
