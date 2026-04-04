package com.pix.pix_service.infrastructure.gateways.qi_tech;

import com.pix.pix_service.common.utils.JsonUtils;
import com.pix.pix_service.domain.exceptions.PixKeyCreationException;
import com.pix.pix_service.domain.gateways.PixKeyGateway;
import com.pix.pix_service.domain.gateways.dtos.CreatePixKeyInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreatePixKeyOutputDTO;
import com.pix.pix_service.domain.gateways.dtos.PixKeyTypeInputDTO;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreatePixKeyRequest;
import com.pix.pix_service.infrastructure.gateways.qi_tech.dtos.QiTechCreatePixKeyResponse;
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
public class QiTechPixKeyGateway implements PixKeyGateway {

    private static final Logger logger = LoggerFactory.getLogger(QiTechPixKeyGateway.class);

    private final String accountKey;
    private final RestClient restClient;
    private final QiTechSigner signer;

    public QiTechPixKeyGateway(
        @Value("${qitech.base-url}") String baseUrl,
        @Value("${qitech.account-key}") String accountKey,
        QiTechSigner signer
    ) {
        this.accountKey = accountKey;
        this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .build();
        this.signer = signer;
    }

    @Override
    public CreatePixKeyOutputDTO createPixKey(CreatePixKeyInputDTO input) {
        String endpoint = "/baas/pix/keys";

        var request = new QiTechCreatePixKeyRequest(
            accountKey,
            input.pixKey(),
            mapPixKeyType(input.pixKeyType())
        );

        String jsonBody = JsonUtils.toJson(request);
        Map<String, String> headers = signer.sign("POST", endpoint, jsonBody);

        ResponseEntity<QiTechCreatePixKeyResponse> response;
        try {
            response = restClient.post()
                .uri(endpoint)
                .header("Authorization", headers.get("Authorization"))
                .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .retrieve()
                .toEntity(QiTechCreatePixKeyResponse.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("Unmapped error while creating pix key", ex);
            throw new PixKeyCreationException();
        } catch (HttpServerErrorException ex) {
            logger.error("Unexpected error while creating pix key", ex);
            throw new PixKeyCreationException();
        }

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new PixKeyCreationException();
        }

        if (response.getBody() == null) {
            throw new PixKeyCreationException();
        }

        return new CreatePixKeyOutputDTO(response.getBody().pixKeyRequestKey());
    }

    private String mapPixKeyType(PixKeyTypeInputDTO type) {
        if (type == PixKeyTypeInputDTO.CPF) return "cpf";
        if (type == PixKeyTypeInputDTO.CNPJ) return "cnpj";
        if (type == PixKeyTypeInputDTO.EMAIL) return "email";
        if (type == PixKeyTypeInputDTO.PHONE) return "phone_number";
        if (type == PixKeyTypeInputDTO.RANDOM) return "random_key";

        throw new IllegalArgumentException("Unsupported pixKeyType: " + type);
    }
}
