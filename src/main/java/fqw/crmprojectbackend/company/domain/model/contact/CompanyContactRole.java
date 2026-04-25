package fqw.crmprojectbackend.company.domain.model.contact;

import java.util.Objects;

public record CompanyContactRole(CompanyContactRoleCode code) {
    public CompanyContactRole {
        Objects.requireNonNull(code);
    }
}
