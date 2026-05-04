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
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;
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
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {
    private final CompanySpringDataRepository companySpringDataRepository;
    private final CompanyClientSegmentSpringDataRepository clientSegmentSpringDataRepository;
    private final CompanyLifecycleStatusSpringDataRepository lifecycleStatusSpringDataRepository;

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
    public CompanyDTO updateByOrigin(CompanyDTO origin) {
        var companyJPA = this.companySpringDataRepository
                .findById(origin.id())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Не найдено компании с идентификатором '%s'",
                        origin.id())));

        companyJPA.setOfficialName(origin.officialName());
        companyJPA.setCommercialName(origin.commercialName());
        companyJPA.setInn(origin.inn());
        companyJPA.setKpp(origin.kpp());

        var clientSegmentCode = origin.clientSegment().code();
        var clientSegmentJPA = this.clientSegmentSpringDataRepository
                .findByCode(clientSegmentCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Не найден сегмент клиента с кодом '%s'",
                        clientSegmentCode)));

        companyJPA.setClientSegment(clientSegmentJPA);

        var lifecycleCode = origin.lifecycleStatus().code();
        var lifecycleJPA = this.lifecycleStatusSpringDataRepository
                .findByCode(lifecycleCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Не найден статус жизненного цикла компании с кодом '%s'",
                        lifecycleCode)));

        companyJPA.setLifecycleStatus(lifecycleJPA);

        var registeredAddressJPA = companyJPA.getRegisteredAddress();
        registeredAddressJPA.updateByOrigin(origin.registeredAddress());
        companyJPA.setRegisteredAddress(registeredAddressJPA);

        var updated = this.companySpringDataRepository.save(companyJPA);

        return CompanyPersistenceMapper.fromEntity(updated);
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
