package fqw.crmprojectbackend.deal.domain.model.process;

import java.time.LocalDate;
import java.util.Objects;

public record DealCloseInfo(LocalDate actualCloseDate, DealLossReason lossReason) {
    public DealCloseInfo {
        Objects.requireNonNull(actualCloseDate);
    }

    public static DealCloseInfo won(LocalDate actualCloseDate) {
        return new DealCloseInfo(actualCloseDate, null);
    }

    public static DealCloseInfo failed(LocalDate actualCloseDate, DealLossReason lossReason) {
        Objects.requireNonNull(lossReason);
        return new DealCloseInfo(actualCloseDate, lossReason);
    }
}
