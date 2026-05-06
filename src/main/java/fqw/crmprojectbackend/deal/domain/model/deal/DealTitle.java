package fqw.crmprojectbackend.deal.domain.model.deal;

import java.util.Objects;

public record DealTitle(String value) {
    public DealTitle {
        Objects.requireNonNull(value);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Название сделки не может быть пустым");
        }
    }
}
