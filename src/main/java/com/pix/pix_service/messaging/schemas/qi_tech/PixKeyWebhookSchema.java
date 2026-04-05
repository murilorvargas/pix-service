package com.pix.pix_service.messaging.schemas.qi_tech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PixKeyWebhookSchema {

    @JsonProperty("webhook_type")
    private String webhookType;

    @JsonProperty("pix_key_request_key")
    private String pixKeyRequestKey;

    @JsonProperty("pix_key_request_status")
    private String pixKeyRequestStatus;

    @JsonProperty("pix_key_status")
    private String pixKeyStatus;

    public PixKeyWebhookSchema() {
    }

    public PixKeyWebhookSchema(
            String webhookType,
            String pixKeyRequestKey,
            String pixKeyRequestStatus,
            String pixKeyStatus
    ) {
        this.webhookType = webhookType;
        this.pixKeyRequestKey = pixKeyRequestKey;
        this.pixKeyRequestStatus = pixKeyRequestStatus;
        this.pixKeyStatus = pixKeyStatus;
    }

    public String getWebhookType() {
        return webhookType;
    }

    public String getPixKeyRequestKey() {
        return pixKeyRequestKey;
    }

    public String getPixKeyRequestStatus() {
        return pixKeyRequestStatus;
    }

    public String getPixKeyStatus() {
        return pixKeyStatus;
    }
}
