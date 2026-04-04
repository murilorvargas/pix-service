package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.PixKeyStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPixKeyStatusRepository extends JpaRepository<PixKeyStatusEntity, Long> {

    Optional<PixKeyStatusEntity> findByEnumerator(String enumerator);
}
