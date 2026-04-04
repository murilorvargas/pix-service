package com.pix.pix_service.infrastructure.gateways.qi_tech;

import com.pix.pix_service.common.utils.Base64Utils;
import com.pix.pix_service.common.utils.JsonUtils;
import com.pix.pix_service.domain.exceptions.QrCodeCreationException;
import com.pix.pix_service.domain.gateways.QrCodeGateway;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeOutputDTO;
import com.pix.pix_service.domain.gateways.dtos.PersonTypeInputDTO;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreateQrCodeRequest;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreateQrCodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class QiTechQrCodeGateway implements QrCodeGateway {

    private static final Logger logger = LoggerFactory.getLogger(QiTechQrCodeGateway.class);

    private final String accountKey;
    private final String pixKey;
    private final RestClient restClient;
    private final QiTechSigner signer;

    public QiTechQrCodeGateway(
        @Value("${qitech.base-url}") String baseUrl,
        @Value("${qitech.account-key}") String accountKey,
        @Value("${qitech.pix-key}") String pixKey,
        QiTechSigner signer
    ) {
        this.accountKey = accountKey;
        this.pixKey = pixKey;
        this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .build();
        this.signer = signer;
    }

    public CreateDynamicInstantQrCodeOutputDTO createDynamicInstantQrCode(CreateDynamicInstantQrCodeInputDTO input) {
        String endpoint = "/baas/qrcode/dynamic";

        var request = new QiTechCreateQrCodeRequest(
            accountKey,
            pixKey,
            input.amount(),
            input.expiration(),
            "registration",
            input.qrCodePayer().documentNumber(),
            input.qrCodePayer().name(),
            mapPersonType(input.qrCodePayer().personType()),
            input.description(),
            input.correlationId(),
            "dynamic_instant"
        );

        String jsonBody = JsonUtils.toJson(request);
        Map<String, String> headers = signer.sign("POST", endpoint, jsonBody);

        ResponseEntity<QiTechCreateQrCodeResponse> response;
        try {
            response = restClient.post()
                .uri(endpoint)
                .header("Authorization", headers.get("Authorization"))
                .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .retrieve()
                .toEntity(QiTechCreateQrCodeResponse.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("Unmapped error while creating dynamic instant qr code", ex);
            throw new QrCodeCreationException();
        } catch (HttpServerErrorException ex) {
            logger.error("Unexpected error while creating dynamic instant qr code", ex);
            throw new QrCodeCreationException();
        }

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new QrCodeCreationException();
        }

        if (response.getBody() == null) {
            throw new QrCodeCreationException();
        }

        return new CreateDynamicInstantQrCodeOutputDTO(
            response.getBody().qrCodeKey(),
            Base64Utils.decode(response.getBody().base64())
        );
    }

    private String mapPersonType(PersonTypeInputDTO type) {
        if (type == PersonTypeInputDTO.NATURAL) return "natural";
        if (type == PersonTypeInputDTO.LEGAL) return "legal";

        throw new IllegalArgumentException("Unsupported personType: " + type.name());
    }
}
