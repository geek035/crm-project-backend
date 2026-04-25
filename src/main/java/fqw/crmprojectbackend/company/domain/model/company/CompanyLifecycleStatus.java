package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;
import java.util.UUID;

public record CompanyLifecycleStatus(CompanyLifecycleStatusType code) {

    public CompanyLifecycleStatus {
        Objects.requireNonNull(code);
    }
}
