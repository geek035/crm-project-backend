package fqw.crmprojectbackend.deal.domain.model.money;

import java.util.Objects;

public record DealCurrency(DealCurrencyCode code) {
    public DealCurrency {
        Objects.requireNonNull(code);
    }
}
