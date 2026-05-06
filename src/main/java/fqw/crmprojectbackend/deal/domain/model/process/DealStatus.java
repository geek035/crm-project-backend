package fqw.crmprojectbackend.deal.domain.model.process;

import java.util.Objects;

public record DealStatus(DealStatusCode code) {
    public DealStatus {
        Objects.requireNonNull(code);
    }
}
