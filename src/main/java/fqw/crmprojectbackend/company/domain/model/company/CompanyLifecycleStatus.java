package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;

public record CompanyLifecycleStatus(CompanyLifecycleStatusCode code) {

    public CompanyLifecycleStatus {
        Objects.requireNonNull(code);
    }
}
