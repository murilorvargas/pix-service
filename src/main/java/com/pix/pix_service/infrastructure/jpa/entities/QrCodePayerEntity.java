package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.QrCodePayer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "QrCodePayer")
public class QrCodePayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr_code_payer_key", nullable = false, unique = true, length = 36)
    private String qrCodePayerKey;

    @Column(nullable = false)
    private String name;

    @Column(name = "document_number", nullable = false, length = 14)
    private String documentNumber;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public QrCodePayerEntity() {
    }

    public QrCodePayerEntity(
            Long id,
            String qrCodePayerKey,
            String name,
            String documentNumber,
            LocalDateTime updatedAt,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.qrCodePayerKey = qrCodePayerKey;
        this.name = name;
        this.documentNumber = documentNumber;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static QrCodePayerEntity fromDomain(QrCodePayer domain) {
        return new QrCodePayerEntity(
                domain.getId(),
                domain.getQrCodePayerKey(),
                domain.getName(),
                domain.getDocumentNumber(),
                domain.getUpdatedAt(),
                domain.getCreatedAt()
        );
    }

    public QrCodePayer toDomain() {
        return new QrCodePayer(
                this.id,
                this.qrCodePayerKey,
                this.name,
                this.documentNumber,
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
