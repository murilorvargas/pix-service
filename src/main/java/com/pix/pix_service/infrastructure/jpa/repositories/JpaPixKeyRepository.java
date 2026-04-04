package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.PixKeyEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaPixKeyRepository extends JpaRepository<PixKeyEntity, Long>, JpaSpecificationExecutor<PixKeyEntity> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM PixKey d WHERE d.id = :id")
    Optional<PixKeyEntity> findByIdForUpdate(@Param("id") Long id);

    Optional<PixKeyEntity> findByPublicKey(String publicKey);
}
