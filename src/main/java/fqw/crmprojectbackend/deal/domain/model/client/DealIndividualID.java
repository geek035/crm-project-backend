package fqw.crmprojectbackend.deal.domain.model.client;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DealIndividualID {
    private final UUID value;

    private DealIndividualID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static DealIndividualID from(UUID id) {
        return new DealIndividualID(id);
    }
}
