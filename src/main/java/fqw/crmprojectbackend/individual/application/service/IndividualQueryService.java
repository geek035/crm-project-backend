package fqw.crmprojectbackend.individual.application.service;

import fqw.crmprojectbackend.individual.application.dto.IndividualDTO;
import fqw.crmprojectbackend.individual.application.dto.IndividualPageDTO;
import fqw.crmprojectbackend.individual.application.query.IndividualByIDQuery;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.application.mapper.IndividualApplicationMapper;
import fqw.crmprojectbackend.individual.application.port.in.IndividualQueryUseCase;
import fqw.crmprojectbackend.individual.application.port.out.IndividualRepositoryPort;
import fqw.crmprojectbackend.individual.domain.model.IndividualID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualQueryService implements IndividualQueryUseCase {

    private final IndividualRepositoryPort individualRepositoryPort;

    @Override
    public Optional<IndividualDTO> findById(IndividualByIDQuery command) {
        return this.individualRepositoryPort
                .findById(IndividualID.from(command.id()))
                .map(IndividualApplicationMapper::toIndividualDTO);
    }

    @Override
    public IndividualPageDTO findByParams(IndividualByParamsQuery command) {
        var data = this.individualRepositoryPort.findByParams(command).stream()
                .map(IndividualApplicationMapper::toIndividualDTO)
                .toList();

        var total = this.individualRepositoryPort.getTotal();

        return new IndividualPageDTO(total, data);
    }
}
