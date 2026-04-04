package com.pix.pix_service.api.schemas.response;

import com.pix.pix_service.domain.entities.PixKey;

public class CreatePixKeyResponseSchema {

    private String publicKey;
    private String pixKey;
    private String type;
    private String status;

    public CreatePixKeyResponseSchema() {
    }

    public CreatePixKeyResponseSchema(
            String publicKey,
            String pixKey,
            String type,
            String status
    ) {
        this.publicKey = publicKey;
        this.pixKey = pixKey;
        this.type = type;
        this.status = status;
    }

    public static CreatePixKeyResponseSchema fromEntity(PixKey entity) {
        return new CreatePixKeyResponseSchema(
                entity.getPublicKey(),
                entity.getPixKey(),
                entity.getPixKeyType() != null
                        ? entity.getPixKeyType().getEnumerator()
                        : null,
                entity.getPixKeyStatus() != null
                        ? entity.getPixKeyStatus().getEnumerator()
                        : null
        );
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPixKey() {
        return pixKey;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
}
