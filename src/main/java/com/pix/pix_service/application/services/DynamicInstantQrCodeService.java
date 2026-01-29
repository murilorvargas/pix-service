package com.pix.pix_service.application.services;

import com.pix.pix_service.application.dtos.input.CreateDynamicInstantQrCodeDTO;
import org.springframework.stereotype.Service;

@Service
public class DynamicInstantQrCodeService {

    public String createDynamicInstantQrCode(CreateDynamicInstantQrCodeDTO dto) {
        return "Hello World!";
    }
}
