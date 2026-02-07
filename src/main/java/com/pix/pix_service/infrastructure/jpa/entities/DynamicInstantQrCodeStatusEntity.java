package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.DynamicInstantQrCodeStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DynamicInstantQrCodeStatus")
public class DynamicInstantQrCodeStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String enumerator;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public DynamicInstantQrCodeStatusEntity() {
    }

    public DynamicInstantQrCodeStatusEntity(
            Long id,
            String enumerator,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.enumerator = enumerator;
        this.createdAt = createdAt;
    }

    public static DynamicInstantQrCodeStatusEntity fromDomain(DynamicInstantQrCodeStatus domain) {
        return new DynamicInstantQrCodeStatusEntity(
                domain.getId(),
                domain.getEnumerator(),
                domain.getCreatedAt()
        );
    }

    public DynamicInstantQrCodeStatus toDomain() {
        return new DynamicInstantQrCodeStatus(
                this.id,
                this.enumerator,
                this.createdAt
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumerator() {
        return enumerator;
    }

    public void setEnumerator(String enumerator) {
        this.enumerator = enumerator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
