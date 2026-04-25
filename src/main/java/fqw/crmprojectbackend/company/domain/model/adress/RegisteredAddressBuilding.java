package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressBuilding(String value) {
    public RegisteredAddressBuilding {
        Objects.requireNonNull(value);
    }
}
