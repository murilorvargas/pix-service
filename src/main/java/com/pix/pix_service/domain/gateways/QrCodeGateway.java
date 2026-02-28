package com.pix.pix_service.domain.gateways;

import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreateDynamicInstantQrCodeOutputDTO;

public interface QrCodeGateway {
    CreateDynamicInstantQrCodeOutputDTO createDynamicInstantQrCode(CreateDynamicInstantQrCodeInputDTO input);
}
