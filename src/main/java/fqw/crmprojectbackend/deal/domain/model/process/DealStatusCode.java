package fqw.crmprojectbackend.deal.domain.model.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealStatusCode {
    OPEN("Open"),
    SUCCESS("Success"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private final String description;

    public static DealStatusCode fromStage(DealStageCode stage) {
        return switch (stage) {
            case WON -> DealStatusCode.SUCCESS;
            case LOST -> DealStatusCode.FAILED;
            case CANCELLED -> DealStatusCode.CANCELLED;
            default -> DealStatusCode.OPEN;
        };
    }
}
