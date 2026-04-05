package com.pix.pix_service.messaging.schemas;

import com.fasterxml.jackson.databind.JsonNode;

public class WebhookSchema {

    private String gateway;
    private JsonNode data;

    public WebhookSchema() {
    }

    public WebhookSchema(String gateway, JsonNode data) {
        this.gateway = gateway;
        this.data = data;
    }

    public String getGateway() {
        return gateway;
    }

    public JsonNode getData() {
        return data;
    }
}
