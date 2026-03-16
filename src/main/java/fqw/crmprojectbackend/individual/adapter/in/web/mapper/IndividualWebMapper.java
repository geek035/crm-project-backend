package fqw.crmprojectbackend.individual.adapter.in.web.mapper;

import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddRequest;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;

public class IndividualWebMapper {
    private IndividualWebMapper() {}

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
