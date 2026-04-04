package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.PixKey;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PixKey")
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_key", nullable = false, unique = true, length = 36)
    private String publicKey;

    @Column(name = "external_key", nullable = true, unique = true, length = 36)
    private String externalKey;

    @Column(name = "pix_key", nullable = true, unique = true, length = 77)
    private String pixKey;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pix_key_type_id", nullable = false)
    private PixKeyTypeEntity pixKeyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pix_key_status_id", nullable = false)
    private PixKeyStatusEntity pixKeyStatus;

    public PixKeyEntity() {
    }

    public PixKeyEntity(
        Long id,
        String publicKey,
        String externalKey,
        String pixKey,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        PixKeyTypeEntity pixKeyType,
        PixKeyStatusEntity pixKeyStatus
    ) {
        this.id = id;
        this.publicKey = publicKey;
        this.externalKey = externalKey;
        this.pixKey = pixKey;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.pixKeyType = pixKeyType;
        this.pixKeyStatus = pixKeyStatus;
    }

    public static PixKeyEntity fromDomain(PixKey domain) {
        return new PixKeyEntity(
            domain.getId(),
            domain.getPublicKey(),
            domain.getExternalKey(),
            domain.getPixKey(),
            domain.getUpdatedAt(),
            domain.getCreatedAt(),
            PixKeyTypeEntity.fromDomain(domain.getPixKeyType()),
            PixKeyStatusEntity.fromDomain(domain.getPixKeyStatus())
        );
    }

    public PixKey toDomain() {
        return new PixKey(
            this.id,
            this.publicKey,
            this.externalKey,
            this.pixKey,
            this.updatedAt,
            this.createdAt,
            this.pixKeyType.toDomain(),
            this.pixKeyStatus.toDomain()
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

    public String getExternalKey() {
        return externalKey;
    }

    public void setExternalKey(String externalKey) {
        this.externalKey = externalKey;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
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

    public PixKeyTypeEntity getPixKeyType() {
        return pixKeyType;
    }

    public void setPixKeyType(PixKeyTypeEntity pixKeyType) {
        this.pixKeyType = pixKeyType;
    }

    public PixKeyStatusEntity getPixKeyStatus() {
        return pixKeyStatus;
    }

    public void setPixKeyStatus(PixKeyStatusEntity pixKeyStatus) {
        this.pixKeyStatus = pixKeyStatus;
    }
}
