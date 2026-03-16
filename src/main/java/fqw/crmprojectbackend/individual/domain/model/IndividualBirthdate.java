package fqw.crmprojectbackend.individual.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public record IndividualBirthdate(LocalDate date) {
    public IndividualBirthdate {
        Objects.requireNonNull(date);
    }
}
