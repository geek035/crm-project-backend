package fqw.crmprojectbackend.individual.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public record Individual(
        IndividualID id,
        IndividualFullName fullName,
        IndividualEmail email,
        IndividualPhoneNumber phoneNumber,
        IndividualBirthdate birthdate
        ) {

    public static Individual restore(
            UUID id,
            String firstName,
            String secondName,
            String surname,
            String email,
            String phoneNumber,
            LocalDate birthdate
    ) {
        return new Individual(
                IndividualID.from(id),
                new IndividualFullName(firstName, secondName, surname),
                new IndividualEmail(email),
                new IndividualPhoneNumber(phoneNumber),
                new IndividualBirthdate(birthdate)
        );
    }
}
