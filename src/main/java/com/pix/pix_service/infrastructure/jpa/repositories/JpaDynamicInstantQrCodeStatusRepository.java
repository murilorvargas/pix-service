package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaDynamicInstantQrCodeStatusRepository extends JpaRepository<DynamicInstantQrCodeStatusEntity, Long> {

    Optional<DynamicInstantQrCodeStatusEntity> findByEnumerator(String enumerator);
}
