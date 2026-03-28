package com.pix.pix_service.domain.exceptions;

public class QrCodeCreationException extends DomainException {

    public QrCodeCreationException() {
        super("Failed to create QR Code");
    }

    @Override
    public String getCode() {
        return "PIX00001";
    }

    @Override
    public String getMessageKey() {
        return "error.qrcode.creation";
    }
}
