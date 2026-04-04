package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.PixKeyType;
import com.pix.pix_service.domain.repositories.PixKeyTypeRepository;
import com.pix.pix_service.infrastructure.jpa.entities.PixKeyTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PixKeyTypeRepositoryImpl implements PixKeyTypeRepository {

    private final JpaPixKeyTypeRepository jpaRepository;

    public PixKeyTypeRepositoryImpl(JpaPixKeyTypeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PixKeyType> findByEnumerator(String enumerator) {
        return jpaRepository.findByEnumerator(enumerator).map(PixKeyTypeEntity::toDomain);
    }
}
