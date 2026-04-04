package com.pix.pix_service.infrastructure.jpa.specifications;

import com.pix.pix_service.infrastructure.jpa.entities.PixKeyEntity;
import org.springframework.data.jpa.domain.Specification;

public class PixKeySpecification {

    public static Specification<PixKeyEntity> withFilters(String publicKey) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (publicKey != null && !publicKey.isBlank()) {
                predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("publicKey"), publicKey));
            }

            return predicate;
        };
    }
}
