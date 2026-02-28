package com.pix.pix_service.infrastructure.gateways.qi_tech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pix.pix_service.domain.gateways.QrCodeGateway;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeOutputDTO;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreateQrCodeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class QiTechQrCodeGateway implements QrCodeGateway {

    private final RestClient restClient;
    private final QiTechSigner signer;
    private final ObjectMapper objectMapper = new ObjectMapper();
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

        String jsonBody = toJson(request);
        Map<String, String> headers = signer.sign("POST", endpoint, jsonBody);

        restClient.post()
                .uri(endpoint)
                .header("Authorization", headers.get("Authorization"))
                .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        return null;
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }
}
