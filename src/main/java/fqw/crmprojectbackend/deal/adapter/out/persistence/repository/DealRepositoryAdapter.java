package fqw.crmprojectbackend.deal.adapter.out.persistence.repository;

import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.deal.adapter.out.persistence.entity.DealJPAEntity;
import fqw.crmprojectbackend.deal.adapter.out.persistence.mapper.DealPersistenceMapper;
import fqw.crmprojectbackend.deal.application.dto.DealDTO;
import fqw.crmprojectbackend.deal.application.port.out.DealRepositoryPort;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;
import fqw.crmprojectbackend.deal.application.request.DealAddRequest;
import fqw.crmprojectbackend.deal.domain.model.deal.DealNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    @Override
    public long getTotal() {
        return this.dealSpringDataRepository.count();
    }

    @Override
    public Optional<DealDTO> findByID(UUID id) {
        return this.dealSpringDataRepository
                .findById(id)
                .map(DealPersistenceMapper::fromEntity);
    }

    @Override
    public List<DealDTO> findByParams(DealQueryParams params) {
        Specification<DealJPAEntity> specification =
                new FilterSpecificationBuilder<DealJPAEntity>()
                        .with(params.filters())
                        .build();

        var orders = params.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(params.pageNumber(), params.pageSize(), sort);
        var page = this.dealSpringDataRepository.findAll(specification, pageRequest);

        return page.stream()
                .map(DealPersistenceMapper::fromEntity)
                .toList();
    }
}
