package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.PixKeyStatus;

import java.util.Optional;

public interface PixKeyStatusRepository {

    Optional<PixKeyStatus> findByEnumerator(String enumerator);
}
