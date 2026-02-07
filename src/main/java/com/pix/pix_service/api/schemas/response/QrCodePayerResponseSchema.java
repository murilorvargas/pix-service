package com.pix.pix_service.api.schemas.response;

import com.pix.pix_service.domain.entities.QrCodePayer;

public class QrCodePayerResponseSchema {

    private String qrCodePayerKey;
    private String name;
    private String documentNumber;

    public QrCodePayerResponseSchema() {
    }

    public QrCodePayerResponseSchema(String qrCodePayerKey, String name, String documentNumber) {
        this.qrCodePayerKey = qrCodePayerKey;
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public static QrCodePayerResponseSchema fromEntity(QrCodePayer entity) {
        return new QrCodePayerResponseSchema(
                entity.getQrCodePayerKey(),
                entity.getName(),
                entity.getDocumentNumber()
        );
    }

    public String getQrCodePayerKey() {
        return qrCodePayerKey;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
