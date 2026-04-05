package com.pix.pix_service.infrastructure.jpa.specifications;

import com.pix.pix_service.infrastructure.jpa.entities.DynamicInstantQrCodeEntity;
import org.springframework.data.jpa.domain.Specification;

public class DynamicInstantQrCodeSpecification {

    public static Specification<DynamicInstantQrCodeEntity> withFilters(Long pixKeyId, String correlationId, String publicKey) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (pixKeyId != null) {
                predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("pixKey").get("id"), pixKeyId));
            }

            if (correlationId != null && !correlationId.isBlank()) {
                predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("correlationId"), correlationId));
            }

            if (publicKey != null && !publicKey.isBlank()) {
                predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("publicKey"), publicKey));
            }

            return predicate;
        };
    }
}
