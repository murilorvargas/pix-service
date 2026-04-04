package com.pix.pix_service.domain.entities;

import java.time.LocalDateTime;

public class PixKey {

    private Long id;
    private String publicKey;
    private String externalKey;
    private String pixKey;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private PixKeyType pixKeyType;
    private PixKeyStatus pixKeyStatus;

    public PixKey() {
    }

    public PixKey(
        String publicKey,
        String pixKey,
        PixKeyType pixKeyType,
        PixKeyStatus pixKeyStatus
    ) {
        this.publicKey = publicKey;
        this.pixKey = pixKey;
        this.pixKeyType = pixKeyType;
        this.pixKeyStatus = pixKeyStatus;
    }

    public PixKey(
        Long id,
        String publicKey,
        String externalKey,
        String pixKey,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        PixKeyType pixKeyType,
        PixKeyStatus pixKeyStatus
    ) {
        this(publicKey, pixKey, pixKeyType, pixKeyStatus);
        this.id = id;
        this.externalKey = externalKey;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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

    public PixKeyType getPixKeyType() {
        return pixKeyType;
    }

    public void setPixKeyType(PixKeyType pixKeyType) {
        this.pixKeyType = pixKeyType;
    }

    public PixKeyStatus getPixKeyStatus() {
        return pixKeyStatus;
    }

    public void setPixKeyStatus(PixKeyStatus pixKeyStatus) {
        this.pixKeyStatus = pixKeyStatus;
    }
}
