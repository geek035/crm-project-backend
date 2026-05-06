package fqw.crmprojectbackend.deal.domain.model.product;

import java.util.Objects;

public record DealProduct(DealProductCode code) {
    public DealProduct {
        Objects.requireNonNull(code);
    }
}
