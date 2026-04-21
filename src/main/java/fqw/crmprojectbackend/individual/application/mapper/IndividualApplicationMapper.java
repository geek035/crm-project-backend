package fqw.crmprojectbackend.individual.application.mapper;

import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.domain.model.*;

public class IndividualApplicationMapper {

    public static IndividualDTO toIndividualDTO(Individual individual) {
        return new IndividualDTO(
                individual.getId().getValue(),
                individual.getFullName().firstName(),
                individual.getFullName().secondName(),
                individual.getFullName().surname(),
                individual.getEmail().value(),
                individual.getPhoneNumber().value(),
                individual.getBirthdate().value()
        );
    }

    public static Individual toIndividualModel(IndividualAddCommand command) {
        return new Individual(
                IndividualID.generateID(),
                new IndividualFullName(command.firstName(), command.secondName(), command.surname()),
                new IndividualEmail(command.email()),
                new IndividualPhoneNumber(command.phoneNumber()),
                new IndividualBirthdate(command.birthdate()));
    }

    public static Individual toIndividualModel(IndividualUpdateCommand command) {
        return new Individual(
                IndividualID.from(command.id()),
                new IndividualFullName(command.firstName(), command.secondName(), command.surname()),
                new IndividualEmail(command.email()),
                new IndividualPhoneNumber(command.phoneNumber()),
                new IndividualBirthdate(command.birthdate()));
    }
}
