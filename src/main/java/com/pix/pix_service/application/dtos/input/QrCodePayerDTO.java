package com.pix.pix_service.application.dtos.input;

public record QrCodePayerDTO(
    String name,
    PersonTypeDTO personTypeDTO,
    String documentNumber
) {}
