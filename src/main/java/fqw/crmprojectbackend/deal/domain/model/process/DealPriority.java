package fqw.crmprojectbackend.deal.domain.model.process;

import java.util.Objects;

public record DealPriority(DealPriorityCode code) {
    public DealPriority {
        Objects.requireNonNull(code);
    }
}
