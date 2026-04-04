package com.pix.pix_service.api.controllers;


import com.pix.pix_service.api.schemas.request.CreatePixKeySchema;
import com.pix.pix_service.api.schemas.response.CreatePixKeyResponseSchema;
import com.pix.pix_service.application.services.PixKeyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PixKeyController {
    private final PixKeyService pix_key_service;

    public PixKeyController(PixKeyService pix_key_service) {
        this.pix_key_service = pix_key_service;
    }

    @PostMapping("/pix/pix_keys")
    public ResponseEntity<CreatePixKeyResponseSchema> createDynamicInstantQrCode(@Valid @RequestBody CreatePixKeySchema request) {
        var dto = request.toDTO();

        var pixKey = this.pix_key_service.createPixKey(dto);
        var response = CreatePixKeyResponseSchema.fromEntity(pixKey);
        return ResponseEntity.status(201).body(response);
    }
}
