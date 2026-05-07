package fqw.crmprojectbackend.deal.application.service;

import fqw.crmprojectbackend.deal.application.command.DealUpdateCommand;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.mapper.DealApplicationMapper;
import fqw.crmprojectbackend.deal.application.port.in.DealUpdateUseCase;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.domain.exception.DealNotExistsException;
import fqw.crmprojectbackend.deal.domain.model.process.DealPriority;
import fqw.crmprojectbackend.deal.domain.model.process.DealPriorityCode;
import fqw.crmprojectbackend.deal.domain.model.process.DealSource;
import fqw.crmprojectbackend.deal.domain.model.process.DealSourceCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealUpdateService implements DealUpdateUseCase {
    private final DealRepositoryPort dealRepositoryPort;

    @Override
    @Transactional
    public DealDTO update(UUID id, DealUpdateCommand command) {
        var deal = this.dealRepositoryPort
                .findByID(id)
                .map(DealApplicationMapper::toDomainModel)
                .orElseThrow(() -> new DealNotExistsException(String.format(
                        "Сделка с идентификатором '%s' не существует",
                        id)));

        deal.changeTitle(command.title());
        deal.updateDescription(command.description());
        deal.changePriority(new DealPriority(DealPriorityCode.getByCode(command.priorityCode())));
        deal.changeSource(new DealSource(DealSourceCode.getByCode(command.sourceCode())));
        deal.changeExpectedCloseDate(command.expectedCloseDate());

        return this.dealRepositoryPort.updateByOrigin(
                DealApplicationMapper.fromDomainModel(deal));
    }
}
