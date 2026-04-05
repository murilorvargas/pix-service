package com.pix.pix_service.domain.repositories;

import com.pix.pix_service.domain.entities.PixKey;

import java.util.List;
import java.util.Optional;

public interface PixKeyRepository {

    PixKey save(PixKey pixKey);

    Optional<PixKey> findById(Long id);

    Optional<PixKey> findByIdForUpdate(Long id);

    Optional<PixKey> findByPublicKey(String publicKey);

    Optional<PixKey> findByExternalKeyForUpdate(String externalKey);

    List<PixKey> findAll(String publicKey, int page, int pageSize);
}
