package fqw.crmprojectbackend.company.adapter.out.persistence.repository.company;

import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.address.RegisteredAddressJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyClientSegmentJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyLifecycleStatusJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.mapper.CompanyPersistenceMapper;
import fqw.crmprojectbackend.company.adapter.out.persistence.mapper.RegisteredAddressPersistenceMapper;
import fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact.CompanyContactSpringDataRepository;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;
import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import fqw.crmprojectbackend.company.application.request.CompanyUpdateRequest;
import fqw.crmprojectbackend.company.domain.model.company.Company;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.company.CompanyINN;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {
    private final CompanySpringDataRepository companySpringDataRepository;

    @Override
    public boolean existByINN(CompanyINN inn) {
        return this.companySpringDataRepository.existsByInn(inn.value());
    }

    @Override
    public boolean existByID(CompanyID id) {
        return this.companySpringDataRepository.existsById(id.getValue());
    }

    @Override
    public CompanyID add(Company company) {
        var entity = CompanyPersistenceMapper.fromDomainModel(company);
        var saved = this.companySpringDataRepository.save(entity);

        return CompanyID.from(saved.getId());
    }

    @Override
    @EntityGraph(attributePaths = {
            "clientSegment",
            "lifecycleStatus",
            "registeredAddress"
    })
    public Optional<Company> findByID(CompanyID id) {
        return this.companySpringDataRepository
                .findById(id.getValue())
                .map(CompanyPersistenceMapper::toDomainModel);
    }

    @Override
    public Optional<Company> findByINN(CompanyINN inn) {
        return this.companySpringDataRepository
                .findByInn(inn.value())
                .map(CompanyPersistenceMapper::toDomainModel);
    }

    @Override
    @EntityGraph(attributePaths = {
            "clientSegment",
            "lifecycleStatus",
            "registeredAddress"
    })
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

    @Override
    public  Company update(CompanyID id, CompanyUpdateRequest request) {
        var companyJPAOptional = this.companySpringDataRepository.findById(id.getValue());

        if (companyJPAOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var companyJPA = companyJPAOptional.get();
        companyJPA.setOfficialName(request.officialName().value());
        companyJPA.setCommercialName(request.commercialName().value());
        companyJPA.setInn(request.inn().value());
        companyJPA.setKpp(request.kpp().value());

        var clientSegmentCode = String.valueOf(request.clientSegment().code());
        var clientSegmentJPA = new CompanyClientSegmentJPAEntity();
        clientSegmentJPA.setCode(clientSegmentCode);
        companyJPA.setClientSegment(clientSegmentJPA);

        var lifeCycleCode = String.valueOf(request.lifecycleStatus().code());
        var lifecycleJPA = new CompanyLifecycleStatusJPAEntity();
        lifecycleJPA.setCode(lifeCycleCode);
        companyJPA.setLifecycleStatus(lifecycleJPA);

        var registeredAddressJPA = updateRegisteredAddressJPAEntity(request, companyJPA);
        companyJPA.setRegisteredAddress(registeredAddressJPA);

        return CompanyPersistenceMapper.toDomainModel(companyJPA);
    }

    private static @NonNull RegisteredAddressJPAEntity updateRegisteredAddressJPAEntity(
            CompanyUpdateRequest request,
            CompanyJPAEntity companyJPA) {
        var requestAddress = request.registeredAddress();
        var registeredAddressJPA = companyJPA.getRegisteredAddress();
        registeredAddressJPA.setCountry(requestAddress.getCountry().value());
        registeredAddressJPA.setRegion(requestAddress.getRegion().value());
        registeredAddressJPA.setCity(requestAddress.getCity().value());
        registeredAddressJPA.setStreet(requestAddress.getStreet().value());
        registeredAddressJPA.setBuilding(requestAddress.getBuilding().value());
        registeredAddressJPA.setOffice(requestAddress.getOffice().value());
        registeredAddressJPA.setPostalCode(requestAddress.getPostalCode().value());
        return registeredAddressJPA;
    }
}
