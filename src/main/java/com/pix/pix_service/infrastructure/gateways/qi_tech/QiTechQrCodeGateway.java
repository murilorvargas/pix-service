package com.pix.pix_service.infrastructure.gateways.qi_tech;

import com.pix.pix_service.common.utils.Base64Utils;
import com.pix.pix_service.common.utils.JsonUtils;
import com.pix.pix_service.domain.exceptions.QrCodeCreationException;
import com.pix.pix_service.domain.gateways.QrCodeGateway;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeOutputDTO;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreateQrCodeRequest;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreateQrCodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class QiTechQrCodeGateway implements QrCodeGateway {

    private final RestClient restClient;
    private final QiTechSigner signer;
    private final String accountKey;
    private final String pixKey;

    public QiTechQrCodeGateway(
        @Value("${qitech.base-url}") String baseUrl,
        @Value("${qitech.account-key}") String accountKey,
        @Value("${qitech.pix-key}") String pixKey,
        QiTechSigner signer
    ) {
        this.signer = signer;
        this.accountKey = accountKey;
        this.pixKey = pixKey;
        this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    public CreateDynamicInstantQrCodeOutputDTO createDynamicInstantQrCode(CreateDynamicInstantQrCodeInputDTO input) {
        String endpoint = "/baas/pix/qrcode";

        var request = new QiTechCreateQrCodeRequest(
                accountKey,
                pixKey,
                input.amount(),
                input.expiration(),
                "registration",
                input.qrCodePayer().documentNumber(),
                input.qrCodePayer().name(),
                input.qrCodePayer().personTypeDTO().name(),
                input.description(),
                input.correlationId(),
                "dynamic_instant"
        );

        String jsonBody = JsonUtils.toJson(request);
        Map<String, String> headers = signer.sign("POST", endpoint, jsonBody);

        ResponseEntity<QiTechCreateQrCodeResponse> response = restClient.post()
            .uri(endpoint)
            .header("Authorization", headers.get("Authorization"))
            .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .toEntity(QiTechCreateQrCodeResponse.class);

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

}
