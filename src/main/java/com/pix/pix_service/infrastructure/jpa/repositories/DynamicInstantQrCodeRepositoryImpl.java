package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeRepository;
import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeEntity;
import com.pix.pix_service.infrastructure.jpa.specifications.DynamicInstantQrCodeSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DynamicInstantQrCodeRepositoryImpl implements DynamicInstantQrCodeRepository {

    private final JpaDynamicInstantQrCodeRepository jpaRepository;

    public DynamicInstantQrCodeRepositoryImpl(JpaDynamicInstantQrCodeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DynamicInstantQrCode save(DynamicInstantQrCode dynamicInstantQrCode) {
        var entity = DynamicInstantQrCodeEntity.fromDomain(dynamicInstantQrCode);
        var saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<DynamicInstantQrCode> findById(Long id) {
        return jpaRepository.findById(id).map(DynamicInstantQrCodeEntity::toDomain);
    }

    @Override
    public Optional<DynamicInstantQrCode> findByIdForUpdate(Long id) {
        return jpaRepository.findByIdForUpdate(id).map(DynamicInstantQrCodeEntity::toDomain);
    }

    @Override
    public Optional<DynamicInstantQrCode> findByDynamicInstantQrCodeKey(String dynamicInstantQrCodeKey) {
        return jpaRepository.findByDynamicInstantQrCodeKey(dynamicInstantQrCodeKey)
                .map(DynamicInstantQrCodeEntity::toDomain);
    }

    @Override
    public Optional<DynamicInstantQrCode> findByCorrelationId(String correlationId) {
        return jpaRepository.findByCorrelationId(correlationId)
                .map(DynamicInstantQrCodeEntity::toDomain);
    }

    @Override
    public List<DynamicInstantQrCode> findAll(String correlationId, String dynamicInstantQrCodeKey, int page, int pageSize) {
        var spec = DynamicInstantQrCodeSpecification.withFilters(correlationId, dynamicInstantQrCodeKey);
        var pageable = PageRequest.of(page - 1, pageSize);
        return jpaRepository.findAll(spec, pageable)
                .map(DynamicInstantQrCodeEntity::toDomain)
                .getContent();
    }
}
