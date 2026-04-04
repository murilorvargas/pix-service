package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.PixKeyStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PixKeyStatus")
public class PixKeyStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String enumerator;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public PixKeyStatusEntity() {
    }

    public PixKeyStatusEntity(
        Long id,
        String enumerator,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.enumerator = enumerator;
        this.createdAt = createdAt;
    }

    public static PixKeyStatusEntity fromDomain(PixKeyStatus domain) {
        return new PixKeyStatusEntity(
            domain.getId(),
            domain.getEnumerator(),
            domain.getCreatedAt()
        );
    }

    public PixKeyStatus toDomain() {
        return new PixKeyStatus(
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
