package fqw.crmprojectbackend.individual.adapter.in.web.mapper;

import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddRequest;
import fqw.crmprojectbackend.individual.adapter.in.web.response.IndividualDTO;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.command.IndividualSelectByIDCommand;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;

public class IndividualWebMapper {
    private IndividualWebMapper() {}

    public static IndividualDTO toDTO(IndividualResponse from) {
        return new IndividualDTO(
                from.id(),
                from.firstName(),
                from.secondName(),
                from.surname(),
                from.email(),
                from.phoneNumber(),
                from.birthdate()
        );
    }

    public static IndividualAddCommand toApplicationModel(IndividualAddRequest from) {
        return new IndividualAddCommand(
                from.firstName(),
                from.secondName(),
                from.surname(),
                from.email(),
                from.phoneNumber(),
                from.birthdate());
    }
}
