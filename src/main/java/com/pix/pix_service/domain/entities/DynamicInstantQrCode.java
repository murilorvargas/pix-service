package com.pix.pix_service.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DynamicInstantQrCode {

    private Long id;
    private String dynamicInstantQrCodeKey;
    private String correlationId;
    private Long qrCodePayerId;
    private BigDecimal amount;
    private String description;
    private Integer expiration;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public DynamicInstantQrCode() {
    }

    public DynamicInstantQrCode(
            Long id,
            String dynamicInstantQrCodeKey,
            String correlationId,
            Long qrCodePayerId,
            BigDecimal amount,
            String description,
            Integer expiration
    ) {
        this.id = id;
        this.dynamicInstantQrCodeKey = dynamicInstantQrCodeKey;
        this.correlationId = correlationId;
        this.qrCodePayerId = qrCodePayerId;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
    }

    public DynamicInstantQrCode(
            Long id,
            String dynamicInstantQrCodeKey,
            String correlationId,
            Long qrCodePayerId,
            BigDecimal amount,
            String description,
            Integer expiration,
            LocalDateTime updatedAt,
            LocalDateTime createdAt
    ) {
        this(id, dynamicInstantQrCodeKey, correlationId, qrCodePayerId, amount, description, expiration);
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDynamicInstantQrCodeKey() {
        return dynamicInstantQrCodeKey;
    }

    public void setDynamicInstantQrCodeKey(String dynamicInstantQrCodeKey) {
        this.dynamicInstantQrCodeKey = dynamicInstantQrCodeKey;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Long getQrCodePayerId() {
        return qrCodePayerId;
    }

    public void setQrCodePayerId(Long qrCodePayerId) {
        this.qrCodePayerId = qrCodePayerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
