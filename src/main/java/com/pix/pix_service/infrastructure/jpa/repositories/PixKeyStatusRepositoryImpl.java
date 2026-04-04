package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.PixKeyStatus;
import com.pix.pix_service.domain.repositories.PixKeyStatusRepository;
import com.pix.pix_service.infrastructure.jpa.entities.PixKeyStatusEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PixKeyStatusRepositoryImpl implements PixKeyStatusRepository {

    private final JpaPixKeyStatusRepository jpaRepository;

    public PixKeyStatusRepositoryImpl(JpaPixKeyStatusRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PixKeyStatus> findByEnumerator(String enumerator) {
        return jpaRepository.findByEnumerator(enumerator).map(PixKeyStatusEntity::toDomain);
    }
}
