package fqw.crmprojectbackend.individual.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public record IndividualBirthdate(LocalDate value) {
    public IndividualBirthdate {
        Objects.requireNonNull(value);
    }
}
