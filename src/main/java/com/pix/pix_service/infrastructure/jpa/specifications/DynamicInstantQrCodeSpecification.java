package com.pix.pix_service.infrastructure.jpa.specifications;

import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeEntity;
import org.springframework.data.jpa.domain.Specification;

public class DynamicInstantQrCodeSpecification {

    public static Specification<DynamicInstantQrCodeEntity> withFilters(String correlationId, String dynamicInstantQrCodeKey) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (correlationId != null && !correlationId.isBlank()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("correlationId"), correlationId));
            }

            if (dynamicInstantQrCodeKey != null && !dynamicInstantQrCodeKey.isBlank()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("dynamicInstantQrCodeKey"), dynamicInstantQrCodeKey));
            }

            return predicate;
        };
    }
}
