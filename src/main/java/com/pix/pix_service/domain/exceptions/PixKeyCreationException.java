package com.pix.pix_service.domain.exceptions;

public class PixKeyCreationException extends DomainException {

    public PixKeyCreationException() {
        super("Failed to create Pix Key");
    }

    @Override
    public String getCode() {
        return "PIX00002";
    }

    @Override
    public String getMessageKey() {
        return "error.pix_key.creation";
    }
}
