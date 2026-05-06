package fqw.crmprojectbackend.company.domain.model.contact.individual;

import java.util.Objects;

public record IndividualContactFullName(String firstName, String secondName, String surname) {

    public IndividualContactFullName {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(secondName);
    }
}
