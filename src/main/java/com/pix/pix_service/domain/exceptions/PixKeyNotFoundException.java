package com.pix.pix_service.domain.exceptions;

public class PixKeyNotFoundException extends DomainException {

    public PixKeyNotFoundException() {
        super("Pix Key not found");
    }

    @Override
    public String getCode() {
        return "PIX00003";
    }

    @Override
    public String getMessageKey() {
        return "error.pix_key.not_found";
    }
}
