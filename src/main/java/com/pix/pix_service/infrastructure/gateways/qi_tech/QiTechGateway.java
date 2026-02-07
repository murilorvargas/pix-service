package com.pix.pix_service.infrastructure.gateways.qi_tech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class QiTechGateway {

    private final RestClient restClient;
    private final QiTechSigner signer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public QiTechGateway(
            @Value("${qitech.base-url}") String baseUrl,
            QiTechSigner signer
    ) {
        this.signer = signer;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public String healthCheck() {
        String endpoint = "/test/" + signer.getApiClientKey();
        Map<String, String> headers = signer.sign("GET", endpoint, "{}");

        return restClient.get()
                .uri(endpoint)
                .header("Authorization", headers.get("Authorization"))
                .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
                .retrieve()
                .body(String.class);
    }

    public <T> T post(String endpoint, Object body, Class<T> responseType) {
        String jsonBody = toJson(body);
        Map<String, String> headers = signer.sign("POST", endpoint, jsonBody);

        return restClient.post()
                .uri(endpoint)
                .header("Authorization", headers.get("Authorization"))
                .header("API-CLIENT-KEY", headers.get("API-CLIENT-KEY"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(responseType);
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }
}
