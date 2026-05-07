package fqw.crmprojectbackend.deal.domain.model.process;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalStatusException;
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

    public static DealStatusCode getByCode(String code) {
        return switch (code) {
            case "OPEN" -> DealStatusCode.OPEN;
            case "SUCCESS" -> DealStatusCode.SUCCESS;
            case "FAILED" -> DealStatusCode.FAILED;
            case "CANCELLED" -> DealStatusCode.CANCELLED;
            default -> throw new DealIllegalStatusException(
                    String.format("Неизвестный статус сделки '%s'", code));
        };
    }

    public static DealStatusCode fromStage(DealStageCode stage) {
        return switch (stage) {
            case WON -> DealStatusCode.SUCCESS;
            case LOST -> DealStatusCode.FAILED;
            case CANCELLED -> DealStatusCode.CANCELLED;
            default -> DealStatusCode.OPEN;
        };
    }
}
