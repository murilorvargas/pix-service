package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.QrCodePayerEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaQrCodePayerRepository extends JpaRepository<QrCodePayerEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT q FROM QrCodePayerEntity q WHERE q.id = :id")
    Optional<QrCodePayerEntity> findByIdForUpdate(@Param("id") Long id);

    Optional<QrCodePayerEntity> findByQrCodePayerKey(String qrCodePayerKey);

    Optional<QrCodePayerEntity> findByDocumentNumber(String documentNumber);
}
