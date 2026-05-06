package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.deal.adapter.out.persistence.mapper.DealPersistenceMapper;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DealRepositoryAdapter implements DealRepositoryPort {
    private final DealSpringDataRepository dealSpringDataRepository;

    @Override
    public boolean existsByNumber(DealNumber number) {
        return this.dealSpringDataRepository.existsByNumber(number.value());
    }

    @Override
    public UUID add(DealAddRequest origin) {
        var deal = DealPersistenceMapper.fromRequest(origin);
        var saved = this.dealSpringDataRepository.save(deal);

        return saved.getId();
    }
}
