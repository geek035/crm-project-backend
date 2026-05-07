package fqw.crmprojectbackend.deal.application.service;

import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.dto.DealPageDTO;
import fqw.crmprojectbackend.deal.application.port.in.DealQueryUseCase;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;
import fqw.crmprojectbackend.deal.domain.exception.DealNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealQueryService implements DealQueryUseCase {
    private final DealRepositoryPort dealRepositoryPort;

    @Override
    public DealDTO findByID(UUID id) {
        return this.dealRepositoryPort.findByID(id)
                .orElseThrow(() -> new DealNotExistsException(String.format(
                        "Сделка с идентификатором '%s' не существует",
                        id)));
    }

    @Override
    public DealPageDTO findByParams(DealQueryParams params) {
        var data = this.dealRepositoryPort.findByParams(params);
        var total = this.dealRepositoryPort.getTotal();

        return new DealPageDTO(total, data);
    }
}
