package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.mapper.CompanyPersistenceMapper;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;
import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;
import fqw.crmprojectbackend.individual.adapter.out.persistence.IndividualSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {
    private final CompanySpringDataRepository companySpringDataRepository;

    private final IndividualSpringDataRepository individualSpringDataRepository;

    @Override
    public boolean existsByINN(CompanyINN inn) {
        return this.companySpringDataRepository.existsByInn(inn.value());
    }

    @Override
    public CompanyID add(Company company) {
        var entity = CompanyPersistenceMapper.fromDomainModel(company);
        var saved = this.companySpringDataRepository.save(entity);

        return CompanyID.from(saved.getId());
    }

    @Override
    public Optional<Company> findByID(CompanyID id) {
        return this.companySpringDataRepository
                .findById(id.getValue())
                .map(CompanyPersistenceMapper::toDomainModel);
    }

    @Override
    public List<Company> findByParams(CompanyQueryParams query) {
        Specification<CompanyJPAEntity> specification =
                new FilterSpecificationBuilder<CompanyJPAEntity>()
                        .with(query.filters())
                        .build();

        var orders = query.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(query.pageNumber(), query.pageSize(), sort);

        var list = this.companySpringDataRepository.findAll(specification, pageRequest);

        return list.stream()
                .map(CompanyPersistenceMapper::toDomainModel)
                .toList();
    }

    @Override
    public long getTotal() {
        return this.companySpringDataRepository.count();
    }
}
