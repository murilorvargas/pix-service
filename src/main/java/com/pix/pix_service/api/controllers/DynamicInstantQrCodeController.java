package com.pix.pix_service.api.controllers;


import com.pix.pix_service.api.schemas.request.CreateDynamicInstantQrCodeSchema;
import com.pix.pix_service.api.schemas.response.CreateDynamicInstantQrCodeResponseSchema;
import com.pix.pix_service.api.schemas.response.ListDynamicInstantQrCodeResponseSchema;
import com.pix.pix_service.api.schemas.response.PaginatedDynamicInstantQrCodeResponseSchema;
import com.pix.pix_service.api.schemas.response.PaginationSchema;
import com.pix.pix_service.application.services.DynamicInstantQrCodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DynamicInstantQrCodeController {
    private final DynamicInstantQrCodeService dynamic_instant_qr_code_service;

    public DynamicInstantQrCodeController(DynamicInstantQrCodeService dynamic_instant_qr_code_service) {
        this.dynamic_instant_qr_code_service = dynamic_instant_qr_code_service;
    }

    @PostMapping("/pix/pix_key/{pixKeyPublicKey}/dynamic_instant_qr_codes")
    public ResponseEntity<CreateDynamicInstantQrCodeResponseSchema> createDynamicInstantQrCode(@PathVariable String pixKeyPublicKey, @Valid @RequestBody CreateDynamicInstantQrCodeSchema request) {
        var dto = request.toDTO();

        var qrCode = this.dynamic_instant_qr_code_service.createDynamicInstantQrCode(pixKeyPublicKey, dto);
        var response = CreateDynamicInstantQrCodeResponseSchema.fromEntity(qrCode);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/pix/dynamic_instant_qr_codes")
    public ResponseEntity<PaginatedDynamicInstantQrCodeResponseSchema> listDynamicInstantQrCodes(
            @RequestParam(required = false) String correlationId,
            @RequestParam(required = false) String publicKey,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "30") Integer pageSize
    ) {
        var qrCodes = this.dynamic_instant_qr_code_service.listDynamicInstantQrCodes(
                correlationId,
                publicKey,
                page,
                pageSize
        );
        var data = qrCodes.stream()
                .map(ListDynamicInstantQrCodeResponseSchema::fromEntity)
                .toList();
        var pagination = new PaginationSchema(page, pageSize);
        var response = new PaginatedDynamicInstantQrCodeResponseSchema(data, pagination);
        return ResponseEntity.ok(response);
    }
}
