package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.DynamicInstantQrCodeStatus;

import java.util.Optional;

public interface PixKeyTypeRepository {

    Optional<DynamicInstantQrCodeStatus> findByEnumerator(String enumerator);
}
