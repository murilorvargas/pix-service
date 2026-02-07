package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.DynamicInstantQrCodeStatus;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeStatusRepository;
import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeStatusEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DynamicInstantQrCodeStatusRepositoryImpl implements DynamicInstantQrCodeStatusRepository {

    private final JpaDynamicInstantQrCodeStatusRepository jpaRepository;

    public DynamicInstantQrCodeStatusRepositoryImpl(JpaDynamicInstantQrCodeStatusRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<DynamicInstantQrCodeStatus> findByEnumerator(String enumerator) {
        return jpaRepository.findByEnumerator(enumerator).map(DynamicInstantQrCodeStatusEntity::toDomain);
    }
}
