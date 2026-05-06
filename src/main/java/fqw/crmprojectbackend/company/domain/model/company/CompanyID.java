package fqw.crmprojectbackend.company.domain.model.company;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class CompanyID {
    private final UUID value;

    private CompanyID(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CompanyID generateID() {
        return new CompanyID(UUID.randomUUID());
    }

    public static CompanyID from (UUID id) {
        return new CompanyID(id);
    }
}
