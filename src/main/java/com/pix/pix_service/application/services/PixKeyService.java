package com.pix.pix_service.application.services;

import com.pix.pix_service.application.dtos.input.CreatePixKeyDTO;
import com.pix.pix_service.domain.UnitOfWork;
import com.pix.pix_service.domain.entities.PixKey;
import com.pix.pix_service.domain.entities.PixKeyStatus;
import com.pix.pix_service.domain.entities.PixKeyType;
import com.pix.pix_service.domain.repositories.PixKeyRepository;
import com.pix.pix_service.domain.repositories.PixKeyStatusRepository;
import com.pix.pix_service.domain.repositories.PixKeyTypeRepository;
import org.slf4j.Logger;

import java.util.UUID;

public class PixKeyService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PixKeyService.class);

    private final UnitOfWork unitOfWork;
    private final PixKeyTypeRepository pixKeyTypeRepository;
    private final PixKeyStatusRepository pixKeyStatusRepository;
    private final PixKeyRepository pixKeyRepository;

    public PixKeyService(
            UnitOfWork unitOfWork,
            PixKeyTypeRepository pixKeyTypeRepository,
            PixKeyStatusRepository pixKeyStatusRepository,
            PixKeyRepository pixKeyRepository
    ) {
        this.unitOfWork = unitOfWork;
        this.pixKeyTypeRepository = pixKeyTypeRepository;
        this.pixKeyStatusRepository = pixKeyStatusRepository;
        this.pixKeyRepository = pixKeyRepository;
    }

    public PixKey createPixKey(CreatePixKeyDTO dto) {
        logger.info("PixKeyService.createPixKey - Starting");

        PixKeyType pixKeyType = pixKeyTypeRepository.findByEnumerator(dto.pixKeyType().name())
            .orElseThrow(() -> new RuntimeException("PixKeyType not found!"));

        PixKeyStatus pixKeyStatus = pixKeyStatusRepository.findByEnumerator("PENDING")
            .orElseThrow(() -> new RuntimeException("PixKeyStatus 'PENDING' not found!"));

        unitOfWork.begin();
        PixKey pixKey = pixKeyRepository.save(new PixKey(
            UUID.randomUUID().toString(),
            dto.pixKey(),
            pixKeyType,
            pixKeyStatus
        ));
        unitOfWork.commit();

        logger.info("PixKeyService.createPixKey - Successfully finished");
        return pixKey;
    }
}
