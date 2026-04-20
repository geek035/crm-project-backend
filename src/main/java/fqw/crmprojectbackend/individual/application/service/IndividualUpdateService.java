package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.port.in.IndividualUpdateUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualUpdateService implements IndividualUpdateUseCase {
    private final IndividualRepository individualRepository;

    @Override
    public IndividualResponse update(IndividualUpdateCommand command) {
        return null;
    }
}
