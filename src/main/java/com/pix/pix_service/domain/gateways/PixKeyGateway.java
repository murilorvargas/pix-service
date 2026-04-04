package com.pix.pix_service.domain.gateways;

import com.pix.pix_service.domain.gateways.dtos.CreatePixKeyInputDTO;
import com.pix.pix_service.domain.gateways.dtos.CreatePixKeyOutputDTO;

public interface PixKeyGateway {

    CreatePixKeyOutputDTO createPixKey(CreatePixKeyInputDTO input);
}
