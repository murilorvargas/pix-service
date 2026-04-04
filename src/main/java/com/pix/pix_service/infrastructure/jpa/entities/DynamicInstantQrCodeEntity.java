package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DynamicInstantQrCode")
public class DynamicInstantQrCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_key", nullable = false, unique = true, length = 36)
    private String publicKey;

    @Column(name = "correlation_id", nullable = false, unique = true, length = 32)
    private String correlationId;

    @Column(name = "external_key", nullable = true, unique = true, length = 36)
    private String externalKey;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 140)
    private String description;

    @Column(nullable = false)
    private Integer expiration;

    @Column(name = "br_code", nullable = true, length = 512)
    private String brCode;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pix_key_id", nullable = false)
    private PixKeyEntity pixKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_code_payer_id", nullable = false)
    private QrCodePayerEntity qrCodePayer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dynamic_instant_qr_code_status_id", nullable = false)
    private DynamicInstantQrCodeStatusEntity dynamicInstantQrCodeStatus;

    public DynamicInstantQrCodeEntity() {
    }

    public DynamicInstantQrCodeEntity(
            Long id,
            String publicKey,
            String correlationId,
            String externalKey,
            BigDecimal amount,
            String description,
            Integer expiration,
            String brCode,
            LocalDateTime updatedAt,
            LocalDateTime createdAt,
            PixKeyEntity pixKey,
            QrCodePayerEntity qrCodePayer,
            DynamicInstantQrCodeStatusEntity dynamicInstantQrCodeStatus
    ) {
        this.id = id;
        this.publicKey = publicKey;
        this.correlationId = correlationId;
        this.externalKey = externalKey;
        this.amount = amount;
        this.description = description;
        this.expiration = expiration;
        this.brCode = brCode;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.pixKey = pixKey;
        this.qrCodePayer = qrCodePayer;
        this.dynamicInstantQrCodeStatus = dynamicInstantQrCodeStatus;
    }

    public static DynamicInstantQrCodeEntity fromDomain(DynamicInstantQrCode domain) {
        return new DynamicInstantQrCodeEntity(
                domain.getId(),
                domain.getPublicKey(),
                domain.getCorrelationId(),
                domain.getExternalKey(),
                domain.getAmount(),
                domain.getDescription(),
                domain.getExpiration(),
                domain.getBrCode(),
                domain.getUpdatedAt(),
                domain.getCreatedAt(),
                PixKeyEntity.fromDomain(domain.getPixKey()),
                QrCodePayerEntity.fromDomain(domain.getQrCodePayer()),
                DynamicInstantQrCodeStatusEntity.fromDomain(domain.getDynamicInstantQrCodeStatus())
        );
    }

    public DynamicInstantQrCode toDomain() {
        return new DynamicInstantQrCode(
                this.id,
                this.publicKey,
                this.correlationId,
                this.externalKey,
                this.amount,
                this.description,
                this.expiration,
                this.brCode,
                this.updatedAt,
                this.createdAt,
                this.pixKey.toDomain(),
                this.qrCodePayer.toDomain(),
                this.dynamicInstantQrCodeStatus.toDomain()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getExternalKey() {
        return externalKey;
    }

    public void setExternalKey(String externalKey) {
        this.externalKey = externalKey;
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

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
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

    public PixKeyEntity getPixKey() {
        return pixKey;
    }

    public void setPixKey(PixKeyEntity pixKey) {
        this.pixKey = pixKey;
    }

    public QrCodePayerEntity getQrCodePayer() {
        return qrCodePayer;
    }

    public void setQrCodePayer(QrCodePayerEntity qrCodePayer) {
        this.qrCodePayer = qrCodePayer;
    }

    public DynamicInstantQrCodeStatusEntity getDynamicInstantQrCodeStatus() {
        return dynamicInstantQrCodeStatus;
    }

    public void setDynamicInstantQrCodeStatus(DynamicInstantQrCodeStatusEntity dynamicInstantQrCodeStatus) {
        this.dynamicInstantQrCodeStatus = dynamicInstantQrCodeStatus;
    }
}
