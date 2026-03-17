package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.individual.application.command.IndividualSelectByIDCommand;
import fqw.crmprojectbackend.individual.application.command.IndividualSelectByParamsCommand;
import fqw.crmprojectbackend.individual.application.mapper.IndividualCommandResponseMapper;
import fqw.crmprojectbackend.individual.application.port.in.IndividualSelectUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualSelectService implements IndividualSelectUseCase {

    private final IndividualRepository individualRepository;

    @Override
    public Optional<IndividualResponse> findById(IndividualSelectByIDCommand command) {
        return this.individualRepository
                .findById(IndividualID.from(command.id()))
                .map(IndividualCommandResponseMapper::toIndividualResponse);
    }

    @Override
    public List<IndividualResponse> findByParams(IndividualSelectByParamsCommand command) {
        return List.of();
    }
}
