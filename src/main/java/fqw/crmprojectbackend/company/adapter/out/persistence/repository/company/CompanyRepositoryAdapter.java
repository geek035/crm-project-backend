package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.company.adapter.out.persistence.mapper.CompanyPersistenceMapper;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {
    private final CompanySpringDataRepository companySpringDataRepository;

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
}
