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
import com.pix.pix_service.domain.gateways.dtos.PersonTypeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.QrCodePayerInputDTO;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeRepository;
import com.pix.pix_service.domain.repositories.DynamicInstantQrCodeStatusRepository;
import com.pix.pix_service.domain.repositories.QrCodePayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DynamicInstantQrCodeService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicInstantQrCodeService.class);

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
        logger.info("DynamicInstantQrCodeService.createDynamicInstantQrCode - Starting");

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
                dto.amount(),
                dto.description(),
                dto.expiration(),
                qrCodePayer,
                pendingDynamicInstantQrCodeStatus
        ));
        unitOfWork.commit();

        try {
            QrCodePayerInputDTO qrCodePayerInputDTO = new QrCodePayerInputDTO(
                qrCodePayer.getName(),
                PersonTypeInputDTO.valueOf(qrCodePayer.getPersonType()),
                qrCodePayer.getDocumentNumber()
            );
            CreateDynamicInstantQrCodeOutputDTO createDynamicInstantQrCodeOutputDTO = qrCodeGateway.createDynamicInstantQrCode(new CreateDynamicInstantQrCodeInputDTO(
                qrCode.getCorrelationId(),
                qrCodePayerInputDTO,
                qrCode.getAmount(),
                qrCode.getDescription(),
                qrCode.getExpiration()
            ));

            DynamicInstantQrCodeStatus activatedDynamicInstantQrCodeStatus = dynamicInstantQrCodeStatusRepository
                .findByEnumerator("active")
                .orElseThrow(() -> new RuntimeException("Status 'active' not found!"));

            qrCode.setExternalKey(createDynamicInstantQrCodeOutputDTO.externalKey());
            qrCode.setBrCode(createDynamicInstantQrCodeOutputDTO.brCode());
            qrCode.setDynamicInstantQrCodeStatus(activatedDynamicInstantQrCodeStatus);

            unitOfWork.begin();
            dynamicInstantQrCodeRepository.save(qrCode);
            unitOfWork.commit();
        } catch (RuntimeException ex) {
            DynamicInstantQrCodeStatus errorDynamicInstantQrCodeStatus = dynamicInstantQrCodeStatusRepository
                .findByEnumerator("error")
                .orElseThrow(() -> new RuntimeException("Status 'error' not found!"));

            qrCode.setDynamicInstantQrCodeStatus(errorDynamicInstantQrCodeStatus);

            unitOfWork.begin();
            dynamicInstantQrCodeRepository.save(qrCode);
            unitOfWork.commit();
            throw ex;
        }

        logger.info("DynamicInstantQrCodeService.createDynamicInstantQrCode -> Successfully finished");
        return qrCode;
    }

    public List<DynamicInstantQrCode> listDynamicInstantQrCodes(String correlationId, String publicKey, int page, int pageSize) {
        return dynamicInstantQrCodeRepository.findAll(correlationId, publicKey, page, pageSize);
    }
}
