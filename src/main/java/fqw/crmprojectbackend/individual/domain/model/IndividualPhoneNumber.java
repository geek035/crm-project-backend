package fqw.crmprojectbackend.individual.domain.model;

import java.util.Objects;

public record IndividualPhoneNumber(String value) {
    public IndividualPhoneNumber {
        Objects.requireNonNull(value);
    }
}
