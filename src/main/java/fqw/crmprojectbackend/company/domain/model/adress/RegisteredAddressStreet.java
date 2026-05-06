package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressStreet(String value) {
    public RegisteredAddressStreet {
        Objects.requireNonNull(value);
    }
}
