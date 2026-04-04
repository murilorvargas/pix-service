package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.PixKeyType;

import java.util.Optional;

public interface PixKeyTypeRepository {

    Optional<PixKeyType> findByEnumerator(String enumerator);
}
