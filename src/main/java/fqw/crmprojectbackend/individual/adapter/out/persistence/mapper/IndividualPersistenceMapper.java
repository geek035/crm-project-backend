package fqw.crmprojectbackend.individual.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.individual.adapter.out.persistence.entity.IndividualJPAEntity;
import fqw.crmprojectbackend.individual.domain.model.Individual;
import org.springframework.stereotype.Component;

public class IndividualPersistenceMapper {
    private IndividualPersistenceMapper() {}

    public static IndividualJPAEntity fromDomainModel(Individual individual) {
        return new IndividualJPAEntity(
                individual.id().getValue(),
                individual.fullName().firstName(),
                individual.fullName().secondName(),
                individual.fullName().surname(),
                individual.email().value(),
                individual.phoneNumber().value(),
                individual.birthdate().value()
        );
    }

    public static Individual toDomainModel(IndividualJPAEntity individual) {
        return Individual.restore(
                individual.getId(),
                individual.getFirstName(),
                individual.getSecondName(),
                individual.getSurname(),
                individual.getEmail(),
                individual.getPhoneNumber(),
                individual.getBirthdate()
        );
    }
}
