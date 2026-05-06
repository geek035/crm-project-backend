package fqw.crmprojectbackend.deal.domain.model.client;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalClientException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealClientTypeCode {
    INDIVIDUAL("Individual client"),
    COMPANY("Company client");

    private final String description;

    public static DealClientTypeCode getByCode(String code) {
        return switch (code) {
            case "INDIVIDUAL" -> DealClientTypeCode.INDIVIDUAL;
            case "COMPANY" -> DealClientTypeCode.COMPANY;
            default -> throw new DealIllegalClientException(
                    String.format("Неизвестный тип клиента сделки '%s'", code));
        };
    }
}
