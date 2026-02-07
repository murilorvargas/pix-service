package com.pix.pix_service.api.controllers;


import com.pix.pix_service.api.schemas.CreateDynamicInstantQrCodeSchema;
import com.pix.pix_service.application.services.DynamicInstantQrCodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicInstantQrCodeController {
    private final DynamicInstantQrCodeService dynamic_instant_qr_code_service;

    public DynamicInstantQrCodeController(DynamicInstantQrCodeService dynamic_instant_qr_code_service) {
        this.dynamic_instant_qr_code_service = dynamic_instant_qr_code_service;
    }

    @PostMapping("/pix/dynamic_instant_qr_codes")
    public ResponseEntity<String> createDynamicInstantQrCode(@Valid @RequestBody CreateDynamicInstantQrCodeSchema request) {
        var dto = request.toDTO();
        var qrCode = this.dynamic_instant_qr_code_service.createDynamicInstantQrCode(dto);
        return ResponseEntity.status(201).body(qrCode.getDynamicInstantQrCodeKey());
    }
}
