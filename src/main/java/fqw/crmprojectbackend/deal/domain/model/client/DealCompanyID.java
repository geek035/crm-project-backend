package fqw.crmprojectbackend.deal.domain.model.client;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DealCompanyID {
    private final UUID value;

    private DealCompanyID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static DealCompanyID from(UUID id) {
        return new DealCompanyID(id);
    }
}
