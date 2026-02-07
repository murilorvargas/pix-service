package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.DynamicInstantQrCode;

import java.util.List;
import java.util.Optional;

public interface DynamicInstantQrCodeRepository {

    DynamicInstantQrCode save(DynamicInstantQrCode dynamicInstantQrCode);

    Optional<DynamicInstantQrCode> findById(Long id);

    Optional<DynamicInstantQrCode> findByIdForUpdate(Long id);

    Optional<DynamicInstantQrCode> findByDynamicInstantQrCodeKey(String dynamicInstantQrCodeKey);

    Optional<DynamicInstantQrCode> findByCorrelationId(String correlationId);

    List<DynamicInstantQrCode> findAll(String correlationId, String dynamicInstantQrCodeKey, int page, int pageSize);
}
