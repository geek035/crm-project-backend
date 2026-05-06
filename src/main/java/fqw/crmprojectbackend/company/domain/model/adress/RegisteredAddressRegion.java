package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressRegion(String value) {
    public RegisteredAddressRegion {
        Objects.requireNonNull(value);
    }
}
