package com.pix.pix_service.api.controllers;

import com.pix.pix_service.api.schemas.request.CreatePixKeySchema;
import com.pix.pix_service.api.schemas.response.*;
import com.pix.pix_service.application.services.PixKeyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pix/pix_keys")
    public ResponseEntity<PaginatedPixKeyResponseSchema> listPixKeys(
        @RequestParam(required = false) String publicKey,
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "30") Integer pageSize
    ) {
        var qrCodes = pix_key_service.listPixKeys(
            publicKey,
            page,
            pageSize
        );
        var data = qrCodes.stream()
            .map(ListPixKeyResponseSchema::fromEntity)
            .toList();
        var pagination = new PaginationSchema(page, pageSize);
        var response = new PaginatedPixKeyResponseSchema(data, pagination);
        return ResponseEntity.ok(response);
    }
}
