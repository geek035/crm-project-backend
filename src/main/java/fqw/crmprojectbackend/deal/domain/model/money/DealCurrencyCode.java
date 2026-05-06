package fqw.crmprojectbackend.deal.domain.model.money;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalCurrencyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealCurrencyCode {
    RUB("Russian ruble"),
    USD("United States dollar"),
    EUR("Euro"),
    CNY("Chinese yuan");

    private final String description;

    public static DealCurrencyCode getByCode(String code) {
        return switch (code) {
            case "RUB" -> DealCurrencyCode.RUB;
            case "USD" -> DealCurrencyCode.USD;
            case "EUR" -> DealCurrencyCode.EUR;
            case "CNY" -> DealCurrencyCode.CNY;
            default -> throw new DealIllegalCurrencyException(
                    String.format("Неизвестная валюта сделки '%s'", code));
        };
    }
}
