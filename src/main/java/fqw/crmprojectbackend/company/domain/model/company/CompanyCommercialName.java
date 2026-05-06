package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;

public record CompanyCommercialName(String value) {
    public CompanyCommercialName {
        Objects.requireNonNull(value);
    }
}
