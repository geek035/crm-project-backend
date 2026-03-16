package fqw.crmprojectbackend.individual.domain.model;

import java.util.Objects;

public record IndividualFullName(String firstName, String secondName, String surname) {

    public IndividualFullName {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(secondName);
    }
}
