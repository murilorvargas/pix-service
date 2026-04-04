package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.infrastructure.jpa.entities.PixKeyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPixKeyTypeRepository extends JpaRepository<PixKeyTypeEntity, Long> {

    Optional<PixKeyTypeEntity> findByEnumerator(String enumerator);
}
