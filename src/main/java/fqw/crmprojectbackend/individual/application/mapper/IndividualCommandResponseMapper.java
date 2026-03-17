package fqw.crmprojectbackend.individual.application.mapper;

import fqw.crmprojectbackend.individual.application.response.IndividualResponse;
import fqw.crmprojectbackend.individual.domain.model.Individual;

public class IndividualCommandResponseMapper {

    public static IndividualResponse toIndividualResponse(Individual individual) {
        return new IndividualResponse(
                individual.id().getValue(),
                individual.fullName().firstName(),
                individual.fullName().secondName(),
                individual.fullName().surname(),
                individual.email().value(),
                individual.phoneNumber().value(),
                individual.birthdate().value()
        );
    }
}
