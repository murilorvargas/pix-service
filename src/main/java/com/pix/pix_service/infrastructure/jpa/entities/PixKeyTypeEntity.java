package com.pix.pix_service.infrastructure.jpa.entities;

import com.pix.pix_service.domain.entities.PixKeyType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PixKeyType")
public class PixKeyTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String enumerator;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public PixKeyTypeEntity() {
    }

    public PixKeyTypeEntity(
        Long id,
        String enumerator,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.enumerator = enumerator;
        this.createdAt = createdAt;
    }

    public static PixKeyTypeEntity fromDomain(PixKeyType domain) {
        return new PixKeyTypeEntity(
            domain.getId(),
            domain.getEnumerator(),
            domain.getCreatedAt()
        );
    }

    public PixKeyType toDomain() {
        return new PixKeyType(
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
