package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.QrCodePayer;

import java.util.Optional;

public interface QrCodePayerRepository {

    QrCodePayer save(QrCodePayer qrCodePayer);

    Optional<QrCodePayer> findById(Long id);

    Optional<QrCodePayer> findByIdForUpdate(Long id);

    Optional<QrCodePayer> findByQrCodePayerKey(String qrCodePayerKey);

    Optional<QrCodePayer> findByDocumentNumber(String documentNumber);
}
