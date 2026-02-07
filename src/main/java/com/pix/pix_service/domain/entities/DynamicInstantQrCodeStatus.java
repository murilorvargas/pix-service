package com.pix.pix_service.domain.entities;

import java.time.LocalDateTime;

public class DynamicInstantQrCodeStatus {

    private Long id;
    private String enumerator;
    private LocalDateTime createdAt;

    public DynamicInstantQrCodeStatus() {
    }

    public DynamicInstantQrCodeStatus(
            Long id,
            String enumerator,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.enumerator = enumerator;
        this.createdAt = createdAt;
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
