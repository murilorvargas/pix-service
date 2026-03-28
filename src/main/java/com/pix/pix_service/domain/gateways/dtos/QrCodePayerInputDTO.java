package com.pix.pix_service.domain.gateways.dtos;

public record QrCodePayerInputDTO(
    String name,
    PersonTypeInputDTO personTypeDTO,
    String documentNumber
) {
}
