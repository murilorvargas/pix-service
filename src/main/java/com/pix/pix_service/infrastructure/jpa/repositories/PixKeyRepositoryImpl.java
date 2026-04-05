package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.PixKey;
import com.pix.pix_service.domain.repositories.PixKeyRepository;
import com.pix.pix_service.infrastructure.jpa.entities.PixKeyEntity;
import com.pix.pix_service.infrastructure.jpa.specifications.PixKeySpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PixKeyRepositoryImpl implements PixKeyRepository {

    private final JpaPixKeyRepository jpaRepository;

    public PixKeyRepositoryImpl(JpaPixKeyRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PixKey save(PixKey pixKey) {
        var entity = PixKeyEntity.fromDomain(pixKey);
        var saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<PixKey> findById(Long id) {
        return jpaRepository.findById(id).map(PixKeyEntity::toDomain);
    }

    @Override
    public Optional<PixKey> findByIdForUpdate(Long id) {
        return jpaRepository.findByIdForUpdate(id).map(PixKeyEntity::toDomain);
    }

    @Override
    public Optional<PixKey> findByPublicKey(String publicKey) {
        return jpaRepository.findByPublicKey(publicKey)
            .map(PixKeyEntity::toDomain);
    }

    @Override
    public Optional<PixKey> findByExternalKeyForUpdate(String externalKey) {
        return jpaRepository.findByExternalKeyForUpdate(externalKey)
            .map(PixKeyEntity::toDomain);
    }

    @Override
    public List<PixKey> findAll(String publicKey, int page, int pageSize) {
        var spec = PixKeySpecification.withFilters(publicKey);
        var pageable = PageRequest.of(page - 1, pageSize);
        return jpaRepository.findAll(spec, pageable)
            .map(PixKeyEntity::toDomain)
            .getContent();
    }
}
