package fqw.crmprojectbackend.individual.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Individual {

    private final IndividualID id;
    private final IndividualFullName fullName;
    private final IndividualEmail email;
    private final IndividualPhoneNumber phoneNumber;
    private final IndividualBirthdate birthdate;

    public Individual(UUID id,
                      String firstName,
                      String secondName,
                      String surname,
                      String email,
                      String phoneNumber,
                      LocalDate birthdate) {
        this.id = IndividualID.from(id);
        this.fullName = new IndividualFullName(firstName, secondName, surname);
        this.email = new IndividualEmail(email);
        this.phoneNumber = new IndividualPhoneNumber(phoneNumber);
        this.birthdate = new IndividualBirthdate(birthdate);
    }
}
