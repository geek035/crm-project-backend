package fqw.crmprojectbackend.company.domain.model.contact;

import java.util.Objects;

public record CompanyContactStatus(CompanyContactStatusCode code) {
    public CompanyContactStatus {
        Objects.requireNonNull(code);
    }
}
