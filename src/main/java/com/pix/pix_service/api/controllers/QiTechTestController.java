package com.pix.pix_service.api.controllers;

import com.pix.pix_service.infrastructure.gateways.qi_tech.QiTechGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QiTechTestController {

    private final QiTechGateway qiTechGateway;

    public QiTechTestController(QiTechGateway qiTechGateway) {
        this.qiTechGateway = qiTechGateway;
    }

    @GetMapping("/qitech/health")
    public String healthCheck() {
        return qiTechGateway.healthCheck();
    }
}
