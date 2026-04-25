package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;

public record CompanyKPP(String value) {
    public CompanyKPP {
        Objects.requireNonNull(value);

        if (value.length() != 9) {
            throw new IllegalArgumentException("КПП должен содержать 9 цифр");
        }
    }
}
