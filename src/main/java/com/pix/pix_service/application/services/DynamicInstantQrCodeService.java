package com.pix.pix_service.application.services;

import com.pix.pix_service.application.dtos.input.CreateDynamicInstantQrCodeDTO;
import com.pix.pix_service.application.dtos.input.QrCodePayerDTO;
import com.pix.pix_service.domain.UnitOfWork;
import com.pix.pix_service.domain.entities.DynamicInstantQrCode;
import com.pix.pix_service.domain.entities.DynamicInstantQrCodeStatus;
import com.pix.pix_service.domain.entities.QrCodePayer;
import com.pix.pix_service.domain.gateways.QrCodeGateway;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeOutputDTO;
import com.pix.pix_service.domain.gateways.dtos.QrCodePayerInputDTO;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeRepository;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeStatusRepository;
import com.pix.pix_service.domain.repositories.QrCodePayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class DynamicInstantQrCodeService {

    private final UnitOfWork unitOfWork;
    private final DynamicInstantQrCodeStatusRepository dynamicInstantQrCodeStatusRepository;
    private final QrCodePayerRepository qrCodePayerRepository;
    private final DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository;
    private final QrCodeGateway qrCodeGateway;

    public DynamicInstantQrCodeService(
            UnitOfWork unitOfWork,
            DynamicInstantQrCodeStatusRepository dynamicInstantQrCodeStatusRepository,
            QrCodePayerRepository qrCodePayerRepository,
            DynamicInstantQrCodeRepository dynamicInstantQrCodeRepository,
            QrCodeGateway qrCodeGateway
    ) {
        this.unitOfWork = unitOfWork;
        this.dynamicInstantQrCodeStatusRepository = dynamicInstantQrCodeStatusRepository;
        this.qrCodePayerRepository = qrCodePayerRepository;
        this.dynamicInstantQrCodeRepository = dynamicInstantQrCodeRepository;
        this.qrCodeGateway = qrCodeGateway;
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

        try {
            QrCodePayerInputDTO qrCodePayerInputDTO = new QrCodePayerInputDTO(
                qrCodePayer.getName(),
                qrCodePayer.
            )
            CreateDynamicInstantQrCodeOutputDTO = qrCodeGateway.createDynamicInstantQrCode(new CreateDynamicInstantQrCodeInputDTO(
                qrCode.getCorrelationId(),
                qrCodePayer,
                qrCode.getAmount(),
                qrCode.getDescription(),
                qrCode.getExpiration()
            ));
        } catch (RuntimeException ex) {
            unitOfWork.commit();
        }

        return qrCode;
    }

    public List<DynamicInstantQrCode> listDynamicInstantQrCodes(String correlationId, String dynamicInstantQrCodeKey, int page, int pageSize) {
        return dynamicInstantQrCodeRepository.findAll(correlationId, dynamicInstantQrCodeKey, page, pageSize);
    }
}
