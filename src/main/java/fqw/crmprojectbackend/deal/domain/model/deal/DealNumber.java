package fqw.crmprojectbackend.deal.domain.model.deal;

import java.util.Objects;

public record DealNumber(String value) {
    public DealNumber {
        Objects.requireNonNull(value);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Номер сделки не может быть пустым");
        }
    }
}
