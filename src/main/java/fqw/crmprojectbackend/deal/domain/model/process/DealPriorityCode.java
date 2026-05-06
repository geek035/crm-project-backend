package fqw.crmprojectbackend.deal.domain.model.process;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalPriorityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealPriorityCode {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High"),
    URGENT("Urgent");

    private final String description;

    public static DealPriorityCode getByCode(String code) {
        return switch (code) {
            case "LOW" -> DealPriorityCode.LOW;
            case "NORMAL" -> DealPriorityCode.NORMAL;
            case "HIGH" -> DealPriorityCode.HIGH;
            case "URGENT" -> DealPriorityCode.URGENT;
            default -> throw new DealIllegalPriorityException(
                    String.format("Неизвестный приоритет сделки '%s'", code));
        };
    }
}
