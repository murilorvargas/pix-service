package com.pix.pix_service.application.services;

import com.pix.pix_service.application.dtos.input.CreateDynamicInstantQrCodeDTO;
import com.pix.pix_service.application.dtos.input.QrCodePayerDTO;
import com.pix.pix_service.domain.UnitOfWork;
import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import com.pix.pix_service.domain.entities.DynamicInstantQrCodeStatus;
import com.pix.pix_service.domain.entities.QrCodePayer;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeRepository;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeStatusRepository;
import com.pix.pix_service.domain.repositories.QrCodePayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DynamicInstantQrCodeService {

    private final UnitOfWork unitOfWork;
    private final DynamicInstantQrCodeStatusRepository dynamicInstantQrCodeStatusRepository;
    private final QrCodePayerRepository qrCodePayerRepository;
    private final DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository;

    public DynamicInstantQrCodeService(
            UnitOfWork unitOfWork,
            DynamicInstantQrCodeStatusRepository dynamicInstantQrCodeStatusRepository,
            QrCodePayerRepository qrCodePayerRepository,
            DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository
    ) {
        this.unitOfWork = unitOfWork;
        this.dynamicInstantQrCodeStatusRepository = dynamicInstantQrCodeStatusRepository;
        this.qrCodePayerRepository = qrCodePayerRepository;
        this.dynamicInstantQrCodeRepository = dynamicInstantQrCodeRepository;
    }

    public DynamicInstantQrCode createDynamicInstantQrCode(CreateDynamicInstantQrCodeDTO dto) {
        DynamicInstantQrCodeStatus pendingDynamicInstantQrCodeStatus = dynamicInstantQrCodeStatusRepository
                .findByEnumerator("pending")
                .orElseThrow(() -> new RuntimeException("Status 'pending' not found!"));

        unitOfWork.begin();
        QrCodePayerDTO payerDto = dto.qrCodePayer();
        QrCodePayer qrCodePayer = qrCodePayerRepository.save(new QrCodePayer(
                UUID.randomUUID().toString(),
                payerDto.name(),
                payerDto.documentNumber()
        ));
        DynamicInstantQrCode qrCode = dynamicInstantQrCodeRepository.save(new DynamicInstantQrCode(
                UUID.randomUUID().toString(),
                dto.correlationId(),
                BigDecimal.valueOf(dto.amount()),
                dto.description(),
                dto.expiration(),
                qrCodePayer,
                pendingDynamicInstantQrCodeStatus
        ));
        unitOfWork.commit();
        return qrCode;
    }
}
