package fqw.crmprojectbackend.deal.domain.model.process;

import java.util.Objects;

public record DealStage(DealStageCode code) {
    public DealStage {
        Objects.requireNonNull(code);
    }
}
