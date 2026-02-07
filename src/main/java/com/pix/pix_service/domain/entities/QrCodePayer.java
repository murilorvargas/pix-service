package com.pix.pix_service.domain.entities;

import java.time.LocalDateTime;

public class QrCodePayer {

    private Long id;
    private String qrCodePayerKey;
    private String name;
    private String documentNumber;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public QrCodePayer() {
    }

    public QrCodePayer(
            String qrCodePayerKey,
            String name,
            String documentNumber
    ) {
        this.qrCodePayerKey = qrCodePayerKey;
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public QrCodePayer(
            Long id,
            String qrCodePayerKey,
            String name,
            String documentNumber,
            LocalDateTime updatedAt,
            LocalDateTime createdAt
    ) {
        this(qrCodePayerKey, name, documentNumber);
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

    public String getQrCodePayerKey() {
        return qrCodePayerKey;
    }

    public void setQrCodePayerKey(String qrCodePayerKey) {
        this.qrCodePayerKey = qrCodePayerKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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
