package com.pix.pix_service.application.services;

import com.pix.pix_service.application.dtos.input.CreateDynamicInstantQrCodeDTO;
import com.pix.pix_service.application.dtos.input.QrCodePayerDTO;
import com.pix.pix_service.domain.UnitOfWork;
import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import com.pix.pix_service.domain.entities.QrCodePayer;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeRepository;
import com.pix.pix_service.domain.repositories.QrCodePayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DynamicInstantQrCodeService {

    private final UnitOfWork unitOfWork;
    private final QrCodePayerRepository qrCodePayerRepository;
    private final DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository;

    public DynamicInstantQrCodeService(
            UnitOfWork unitOfWork,
            QrCodePayerRepository qrCodePayerRepository,
            DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository
    ) {
        this.unitOfWork = unitOfWork;
        this.qrCodePayerRepository = qrCodePayerRepository;
        this.dynamicInstantQrCodeRepository = dynamicInstantQrCodeRepository;
    }

    public DynamicInstantQrCode createDynamicInstantQrCode(CreateDynamicInstantQrCodeDTO dto) {
        unitOfWork.begin();
        try {
            QrCodePayerDTO payerDto = dto.qrCodePayer();
            QrCodePayer payer = qrCodePayerRepository.save(new QrCodePayer(
                    null,
                    UUID.randomUUID().toString(),
                    payerDto.name(),
                    payerDto.documentNumber()
            ));

            DynamicInstantQrCode qrCode = dynamicInstantQrCodeRepository.save(new DynamicInstantQrCode(
                    null,
                    UUID.randomUUID().toString(),
                    dto.correlationId(),
                    payer.getId(),
                    BigDecimal.valueOf(dto.amount()),
                    dto.description(),
                    dto.expiration()
            ));

            unitOfWork.commit();
            return qrCode;
        } catch (Exception e) {
            unitOfWork.rollback();
            throw e;
        }
    }
}
