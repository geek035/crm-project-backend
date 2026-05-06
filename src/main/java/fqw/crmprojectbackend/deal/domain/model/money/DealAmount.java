package fqw.crmprojectbackend.deal.domain.model.money;

import fqw.crmprojectbackend.deal.domain.exception.DealIllegalAmountException;

import java.math.BigDecimal;
import java.util.Objects;

public record DealAmount(BigDecimal value, DealCurrency currency) {
    public DealAmount {
        Objects.requireNonNull(value);
        Objects.requireNonNull(currency);

        if (value.signum() <= 0) {
            throw new DealIllegalAmountException("Сумма сделки должна быть больше нуля");
        }
    }

    public static DealAmount of(BigDecimal value, String currencyCode) {
        return new DealAmount(
                value,
                new DealCurrency(DealCurrencyCode.getByCode(currencyCode)));
    }
}
