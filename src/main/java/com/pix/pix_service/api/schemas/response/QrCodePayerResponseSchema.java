package com.pix.pix_service.api.schemas.response;

import com.pix.pix_service.domain.entities.QrCodePayer;

public class QrCodePayerResponseSchema {

    private String publicKey;
    private String name;
    private String documentNumber;

    public QrCodePayerResponseSchema() {
    }

    public QrCodePayerResponseSchema(String publicKey, String name, String documentNumber) {
        this.publicKey = publicKey;
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public static QrCodePayerResponseSchema fromEntity(QrCodePayer entity) {
        return new QrCodePayerResponseSchema(
                entity.getPublicKey(),
                entity.getName(),
                entity.getDocumentNumber()
        );
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
