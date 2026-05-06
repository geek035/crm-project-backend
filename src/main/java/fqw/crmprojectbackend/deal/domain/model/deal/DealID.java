package fqw.crmprojectbackend.deal.domain.model.deal;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DealID {
    private final UUID value;

    private DealID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static DealID generateID() {
        return new DealID(UUID.randomUUID());
    }

    public static DealID from(UUID id) {
        return new DealID(id);
    }
}
