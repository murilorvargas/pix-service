package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaDynamicInstantQrCodeRepository extends JpaRepository<DynamicInstantQrCodeEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM DynamicInstantQrCodeEntity d WHERE d.id = :id")
    Optional<DynamicInstantQrCodeEntity> findByIdForUpdate(@Param("id") Long id);

    Optional<DynamicInstantQrCodeEntity> findByDynamicInstantQrCodeKey(String dynamicInstantQrCodeKey);

    Optional<DynamicInstantQrCodeEntity> findByCorrelationId(String correlationId);
}
