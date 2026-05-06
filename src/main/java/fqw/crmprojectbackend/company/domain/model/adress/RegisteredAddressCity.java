package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressCity(String value) {
    public RegisteredAddressCity {
        Objects.requireNonNull(value);
    }
}
