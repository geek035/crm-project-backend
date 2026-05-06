package fqw.crmprojectbackend.deal.domain.model.process;

import java.util.Objects;

public record DealSource(DealSourceCode code) {
    public DealSource {
        Objects.requireNonNull(code);
    }
}
