package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;

public record CompanyOfficialName(String value) {
    public CompanyOfficialName {
        Objects.requireNonNull(value);
    }
}
