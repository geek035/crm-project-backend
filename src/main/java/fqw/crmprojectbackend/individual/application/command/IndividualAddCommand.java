package fqw.crmprojectbackend.individual.application.command;

import java.time.LocalDate;
import java.util.Objects;

public record IndividualAddCommand(
        String firstName,
        String secondName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthdate
) {
    public IndividualAddCommand {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(secondName);
        Objects.requireNonNull(email);
        Objects.requireNonNull(birthdate);
    }
}
