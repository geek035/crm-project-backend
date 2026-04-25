package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressCountry(String value) {
    public RegisteredAddressCountry {
        Objects.requireNonNull(value);
    }
}
