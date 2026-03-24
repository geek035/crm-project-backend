package fqw.crmprojectbackend.individual.application.query;

import java.util.Objects;
import java.util.UUID;

public record IndividualByIDQuery(UUID id) {
    public IndividualByIDQuery {
        Objects.requireNonNull(id);
    }
}
