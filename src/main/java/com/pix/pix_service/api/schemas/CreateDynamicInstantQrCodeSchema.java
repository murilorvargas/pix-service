package com.pix.pix_service.api.schemas;

import com.pix.pix_service.application.dtos.input.CreateDynamicInstantQrCodeDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
public class CreateDynamicInstantQrCodeSchema {

    @NotNull
    @Size(min = 32, max = 32)
    private String correlationId;

    @NotNull
    @Valid
    private QrCodePayerSchema qrCodePayer;

    @NotNull
    @DecimalMin(value = "0.01")
    private Double amount;

    @Size(min = 1, max = 140)
    private String description;

    @NotNull
    @Min(1)
    private Integer expiration;

    public String getCorrelationId() {
        return correlationId;
    }

    public QrCodePayerSchema getQrCodePayer() {
        return qrCodePayer;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public CreateDynamicInstantQrCodeDTO toDTO() {
        return new CreateDynamicInstantQrCodeDTO(
            this.correlationId,
            this.qrCodePayer.toDTO(),
            this.amount,
            this.description,
            this.expiration
        );
    }
}
