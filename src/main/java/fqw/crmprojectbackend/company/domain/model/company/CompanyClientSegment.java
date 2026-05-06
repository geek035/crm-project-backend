package fqw.crmprojectbackend.company.domain.model.company;

import java.util.Objects;
import java.util.UUID;

public record CompanyClientSegment(CompanyClientSegmentCode code) {

    public CompanyClientSegment {
        Objects.requireNonNull(code);
    }
}
