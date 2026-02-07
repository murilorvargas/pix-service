package com.pix.pix_service.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DynamicInstantQrCode {

    private Long id;
    private String dynamicInstantQrCodeKey;
    private String correlationId;
    private BigDecimal amount;
    private String description;
    private Integer expiration;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private QrCodePayer qrCodePayer;
    private DynamicInstantQrCodeStatus dynamicInstantQrCodeStatus;

    public DynamicInstantQrCode() {
    }

    public DynamicInstantQrCode(
            String dynamicInstantQrCodeKey,
            String correlationId,
            BigDecimal amount,
            String description,
            Integer expiration,
            QrCodePayer qrCodePayer,
            DynamicInstantQrCodeStatus dynamicInstantQrCodeStatus
    ) {
        this.dynamicInstantQrCodeKey = dynamicInstantQrCodeKey;
        this.correlationId = correlationId;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
        this.qrCodePayer = qrCodePayer;
        this.dynamicInstantQrCodeStatus = dynamicInstantQrCodeStatus;
    }

    public DynamicInstantQrCode(
            Long id,
            String dynamicInstantQrCodeKey,
            String correlationId,
            BigDecimal amount,
            String description,
            Integer expiration,
            LocalDateTime updatedAt,
            LocalDateTime createdAt,
            QrCodePayer qrCodePayer,
            DynamicInstantQrCodeStatus dynamicInstantQrCodeStatus
    ) {
        this(dynamicInstantQrCodeKey, correlationId, amount, description, expiration, qrCodePayer, dynamicInstantQrCodeStatus);
        this.id = id;
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

    public QrCodePayer getQrCodePayer() {
        return qrCodePayer;
    }

    public void setQrCodePayer(QrCodePayer qrCodePayer) {
        this.qrCodePayer = qrCodePayer;
    }

    public DynamicInstantQrCodeStatus getDynamicInstantQrCodeStatus() {
        return dynamicInstantQrCodeStatus;
    }

    public void setDynamicInstantQrCodeStatus(DynamicInstantQrCodeStatus dynamicInstantQrCodeStatus) {
        this.dynamicInstantQrCodeStatus = dynamicInstantQrCodeStatus;
    }
}
