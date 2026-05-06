package fqw.crmprojectbackend.deal.domain.model.client;

import java.util.Objects;

public record DealClientType(DealClientTypeCode code) {
    public DealClientType {
        Objects.requireNonNull(code);
    }
}
