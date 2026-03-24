package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.application.mapper.IndividualCommandResponseMapper;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepository;
import fqw.crmprojectbackend.individual.application.response.IndividualQueryResponse;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualQueryService implements IndividualQueryUseCase {

    private final IndividualRepository individualRepository;

    @Override
    public Optional<IndividualResponse> findById(IndividualByIDQuery command) {
        return this.individualRepository
                .findById(IndividualID.from(command.id()))
                .map(IndividualCommandResponseMapper::toIndividualResponse);
    }

    @Override
    public IndividualQueryResponse findByParams(IndividualByParamsQuery command) {
        var data = this.individualRepository.findByParams(command).stream()
                .map(IndividualCommandResponseMapper::toIndividualResponse)
                .toList();

        var total = this.individualRepository.getTotal();

        return new IndividualQueryResponse(total, data);
    }
}
