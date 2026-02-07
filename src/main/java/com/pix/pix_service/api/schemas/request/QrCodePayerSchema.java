package com.pix.pix_service.api.schemas.request;

import com.pix.pix_service.application.dtos.input.QrCodePayerDTO;
import com.pix.pix_service.application.dtos.input.PersonTypeDTO;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

public class QrCodePayerSchema {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private PersonTypeSchema personType;

    @NotBlank
    private String documentNumber;

    public @NotBlank @Size(max = 255) String getName() {
        return name;
    }

    public @NotNull PersonTypeSchema getPersonType() {
        return personType;
    }

    public @NotBlank String getDocumentNumber() {
        return documentNumber;
    }

    public QrCodePayerDTO toDTO() {
        return new QrCodePayerDTO(
            this.name,
            PersonTypeDTO.valueOf(this.personType.name()),
            this.documentNumber
        );
    }
}
