package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import com.pix.pix_service.domain.entities.PixKey;

import java.util.List;
import java.util.Optional;

public interface DynamicInstantQrCodeRepository {

    DynamicInstantQrCode save(DynamicInstantQrCode dynamicInstantQrCode);

    Optional<DynamicInstantQrCode> findById(Long id);

    Optional<DynamicInstantQrCode> findByIdForUpdate(Long id);

    Optional<DynamicInstantQrCode> findByPublicKey(String publicKey);

    Optional<DynamicInstantQrCode> findByCorrelationId(String correlationId);

    List<DynamicInstantQrCode> findAll(PixKey pixKey, String correlationId, String publicKey, int page, int pageSize);
}
