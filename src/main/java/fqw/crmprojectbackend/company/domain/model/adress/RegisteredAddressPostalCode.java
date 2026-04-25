package fqw.crmprojectbackend.company.domain.model.adress;

import java.util.Objects;

public record RegisteredAddressPostalCode(String value) {
    public RegisteredAddressPostalCode {
        Objects.requireNonNull(value);

        if (value.length() != 6) {
            throw new IllegalArgumentException("Почтовый индекс должен содержать 6 цифр");
        }
    }
}
