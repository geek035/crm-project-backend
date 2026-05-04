package fqw.crmprojectbackend.individual.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.individual.adapter.out.persistence.entity.IndividualJPAEntity;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.domain.model.Individual;
import org.springframework.stereotype.Component;

public class IndividualPersistenceMapper {
    private IndividualPersistenceMapper() {}

    public static IndividualDTO fromEntity(IndividualJPAEntity entity) {
        return new IndividualDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getBirthdate());
    }

    public static IndividualJPAEntity fromDomainModel(Individual individual) {
        return new IndividualJPAEntity(
                individual.getId().getValue(),
                individual.getFullName().firstName(),
                individual.getFullName().secondName(),
                individual.getFullName().surname(),
                individual.getEmail().value(),
                individual.getPhoneNumber().value(),
                individual.getBirthdate().value()
        );
    }

    public static Individual toDomainModel(IndividualJPAEntity individual) {
        return new Individual(
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
