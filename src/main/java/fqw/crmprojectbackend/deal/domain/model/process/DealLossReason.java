package fqw.crmprojectbackend.deal.domain.model.process;

import java.util.Objects;

public record DealLossReason(DealLossReasonCode code) {
    public DealLossReason {
        Objects.requireNonNull(code);
    }
}
