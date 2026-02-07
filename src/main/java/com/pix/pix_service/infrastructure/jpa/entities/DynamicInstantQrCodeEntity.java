package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DynamicInstantQrCode")
public class DynamicInstantQrCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dynamic_instant_qrcode_key", nullable = false, unique = true, length = 36)
    private String dynamicInstantQrCodeKey;

    @Column(name = "correlation_id", nullable = false, unique = true, length = 32)
    private String correlationId;

    @Column(name = "qr_code_payer_id", nullable = false)
    private Long qrCodePayerId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 140)
    private String description;

    @Column(nullable = false)
    private Integer expiration;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public DynamicInstantQrCodeEntity() {
    }

    public DynamicInstantQrCodeEntity(
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
        this.id = id;
        this.dynamicInstantQrCodeKey = dynamicInstantQrCodeKey;
        this.correlationId = correlationId;
        this.qrCodePayerId = qrCodePayerId;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static DynamicInstantQrCodeEntity fromDomain(DynamicInstantQrCode domain) {
        return new DynamicInstantQrCodeEntity(
                domain.getId(),
                domain.getDynamicInstantQrCodeKey(),
                domain.getCorrelationId(),
                domain.getQrCodePayerId(),
                domain.getAmount(),
                domain.getDescription(),
                domain.getExpiration(),
                domain.getUpdatedAt(),
                domain.getCreatedAt()
        );
    }

    public DynamicInstantQrCode toDomain() {
        return new DynamicInstantQrCode(
                this.id,
                this.dynamicInstantQrCodeKey,
                this.correlationId,
                this.qrCodePayerId,
                this.amount,
                this.description,
                this.expiration,
                this.updatedAt,
                this.createdAt
        );
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
