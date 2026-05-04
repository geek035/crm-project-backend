package fqw.crmprojectbackend.company.adapter.out.persistence.repository.contact;

import fqw.crmprojectbackend.common.persistent.jpa.spectification.FilterSpecificationBuilder;
import fqw.crmprojectbackend.common.query.BaseQueryParams;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.company.CompanyJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactRoleJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactStatusJPAEntity;
import fqw.crmprojectbackend.company.adapter.out.persistence.mapper.CompanyContactPersistentMapper;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.application.port.out.IndividualContactGateway;
import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import fqw.crmprojectbackend.company.application.request.CompanyContactUpdateRequest;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContactID;
import jakarta.persistence.EntityNotFoundException;
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
public class CompanyContactRepositoryAdapter implements CompanyContactRepositoryPort {
    private final CompanyContactSpringDataRepository contactSpringDataRepository;
    private final IndividualContactGateway individualContactGateway;
    private final CompanyContactRoleSpringDataRepository roleSpringDataRepository;
    private final CompanyContactStatusSpringDataRepository statusSpringDataRepository;

    @Override
    public boolean existByIndividualID(UUID individualID) {
        return this.contactSpringDataRepository.existsByIndividualID(individualID);
    }

    @Override
    public UUID addContact(CompanyContactAddRequest request) {
        var contact = new CompanyContactJPAEntity();
        contact.setId(request.id());
        contact.setIndividualID(request.individualID());

        var company = new CompanyJPAEntity();
        company.setId(request.companyID());
        contact.setCompany(company);

        var role = new CompanyContactRoleJPAEntity();
        role.setCode(request.roleCode());
        contact.setRole(role);

        var status = new CompanyContactStatusJPAEntity();
        status.setCode(request.statusCode());
        contact.setStatus(status);

        var saved = this.contactSpringDataRepository.save(contact);

        return saved.getId();
    }

    @Override
    public long getTotal() {
        return this.contactSpringDataRepository.count();
    }

    @Override
    public Optional<CompanyContactDTO> findByID(UUID id) {
        var contactOptional = this.contactSpringDataRepository.findById(id);

        if (contactOptional.isEmpty()) {
            return Optional.empty();
        }

        var contact = contactOptional.get();
        var individualID = IndividualContactID.from(contact.getIndividualID());
        var individualOptional = this.individualContactGateway.getByID(individualID);

        if (individualOptional.isEmpty()) {
            return Optional.empty();
        }

        var individual = individualOptional.get();
        return Optional.of(CompanyContactPersistentMapper.fromEntity(contact, individual));
    }

    @Override
    public List<CompanyContactDTO> findByParams(UUID id, BaseQueryParams params) {
        Specification<CompanyContactJPAEntity> specification =
                new FilterSpecificationBuilder<CompanyContactJPAEntity>()
                        .with(params.filters())
                        .with(new FilterCriterion(
                                "company.id",
                                id,
                                FilterCriterionMatchMode.EQUALS))
                        .build();

        var orders = params.sort().stream()
                .map(it -> new Sort.Order(
                        Sort.Direction.fromString(it.direction().toString().toLowerCase()),
                        it.field()))
                .toList();

        var sort = Sort.by(orders);
        var pageRequest = PageRequest.of(params.pageNumber(), params.pageSize(), sort);
        var contacts = this.contactSpringDataRepository.findAll(specification, pageRequest);

        var ids = contacts.stream().map(CompanyContactJPAEntity::getIndividualID).toList();
        var individuals = this.individualContactGateway.getByIDs(ids);

        return contacts
                .stream()
                .map(it -> CompanyContactPersistentMapper
                        .fromEntity(it, individuals.get(it.getIndividualID())))
                .toList();
    }

    @Override
    public CompanyContactDTO updateByOrigin(CompanyContactDTO origin) {
        var contact = this.contactSpringDataRepository
                .findById(origin.id())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Контакт с идентификатором '%s' не найден",
                        origin.id())));

        var individualID = IndividualContactID.from(contact.getIndividualID());
        var individual = this.individualContactGateway
                .getByID(individualID)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Физ. лицо с идентификатором '%s' не найден",
                        individualID.getValue())));

        contact.setIndividualID(individualID.getValue());

        var role = this.roleSpringDataRepository
                .findByCode(origin.role().code())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Роль контакта с кодом '%s' не найдена",
                        origin.role().code())));

        contact.setRole(role);

        var status = this.statusSpringDataRepository
                .findByCode(origin.status().code())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Статус контакта с кодом '%s' не найден",
                        origin.status().code())));

        contact.setStatus(status);

        var updated = this.contactSpringDataRepository.save(contact);

        return CompanyContactPersistentMapper.fromEntity(updated, individual);
    }
}
