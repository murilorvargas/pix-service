package com.pix.pix_service.infrastructure.jpa.repositories;

import com.pix.pix_service.domain.entities.QrCodePayer;
import com.pix.pix_service.domain.repositories.QrCodePayerRepository;
import com.pix.pix_service.infrastructure.jpa.entities.QrCodePayerEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QrCodePayerRepositoryImpl implements QrCodePayerRepository {

    private final JpaQrCodePayerRepository jpaRepository;

    public QrCodePayerRepositoryImpl(JpaQrCodePayerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public QrCodePayer save(QrCodePayer qrCodePayer) {
        var entity = QrCodePayerEntity.fromDomain(qrCodePayer);
        var saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<QrCodePayer> findById(Long id) {
        return jpaRepository.findById(id).map(QrCodePayerEntity::toDomain);
    }

    @Override
    public Optional<QrCodePayer> findByIdForUpdate(Long id) {
        return jpaRepository.findByIdForUpdate(id).map(QrCodePayerEntity::toDomain);
    }

    @Override
    public Optional<QrCodePayer> findByQrCodePayerKey(String qrCodePayerKey) {
        return jpaRepository.findByQrCodePayerKey(qrCodePayerKey).map(QrCodePayerEntity::toDomain);
    }

    @Override
    public Optional<QrCodePayer> findByDocumentNumber(String documentNumber) {
        return jpaRepository.findByDocumentNumber(documentNumber).map(QrCodePayerEntity::toDomain);
    }

}
