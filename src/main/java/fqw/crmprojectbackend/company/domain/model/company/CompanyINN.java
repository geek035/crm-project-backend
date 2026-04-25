package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;

public record CompanyINN(String value) {
    public CompanyINN {
        Objects.requireNonNull(value);

        if (value.length() != 10) {
            throw new IllegalArgumentException("ИНН должен содержать 10 цифр для юр. лиц");
        }
    }
}
