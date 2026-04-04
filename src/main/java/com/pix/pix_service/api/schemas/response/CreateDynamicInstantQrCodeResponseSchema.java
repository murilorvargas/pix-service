package com.pix.pix_service.api.schemas.response;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;

import java.math.BigDecimal;

public class CreateDynamicInstantQrCodeResponseSchema {

    private String publicKey;
    private String correlationId;
    private BigDecimal amount;
    private String description;
    private Integer expiration;
    private String brCode;
    private String status;
    private QrCodePayerResponseSchema qrCodePayer;

    public CreateDynamicInstantQrCodeResponseSchema() {
    }

    public CreateDynamicInstantQrCodeResponseSchema(
            String publicKey,
            String correlationId,
            BigDecimal amount,
            String description,
            Integer expiration,
            String brCode,
            String status,
            QrCodePayerResponseSchema qrCodePayer
    ) {
        this.publicKey = publicKey;
        this.correlationId = correlationId;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
        this.brCode = brCode;
        this.status = status;
        this.qrCodePayer = qrCodePayer;
    }

    public static CreateDynamicInstantQrCodeResponseSchema fromEntity(DynamicInstantQrCode entity) {
        return new CreateDynamicInstantQrCodeResponseSchema(
                entity.getPublicKey(),
                entity.getCorrelationId(),
                entity.getAmount(),
                entity.getDescription(),
                entity.getExpiration(),
                entity.getBrCode(),
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

    public String getBrCode() {
        return brCode;
    }

    public String getStatus() {
        return status;
    }

    public QrCodePayerResponseSchema getQrCodePayer() {
        return qrCodePayer;
    }
}
