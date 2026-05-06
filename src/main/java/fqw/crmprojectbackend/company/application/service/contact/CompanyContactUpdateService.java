package fqw.crmprojectbackend.company.application.service.contact;

import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateRoleCommand;
import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateStatusCommand;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.mapper.CompanyContactApplicationMapper;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactUpdateUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.application.port.out.IndividualContactGateway;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactNotExistsException;
import fqw.crmprojectbackend.company.domain.exception.contact.IndividualContactNotFoundException;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactRoleCode;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactStatusCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyContactUpdateService implements CompanyContactUpdateUseCase {
    private final CompanyContactRepositoryPort contactRepositoryPort;
    private final IndividualContactGateway individualContactGateway;

    @Override
    @Transactional
    public CompanyContactDTO updateRole(UUID id, CompanyContactUpdateRoleCommand command) {
        var contact = this.contactRepositoryPort
                .findByID(id)
                .map(CompanyContactApplicationMapper::toDomainModel)
                .orElseThrow(() -> new CompanyContactNotExistsException(String.format(
                        "Контакта с идентификатором '%s' не существует",
                        id)));

        var individual = this.individualContactGateway
                .getByID(contact.getIndividualContact().getId())
                .orElseThrow(() -> new IndividualContactNotFoundException(String.format(
                        "Не удалось найти физ. лицо с идентификатором '%s'",
                        contact.getIndividualContact().getId().getValue())));

        var role = CompanyContactRoleCode.getByCode(command.roleCode());
        contact.changeRole(role);

        return this.contactRepositoryPort.updateByOrigin(
                CompanyContactApplicationMapper.fromDomainModel(contact, individual));
    }

    @Override
    public CompanyContactDTO updateStatus(UUID id, CompanyContactUpdateStatusCommand command) {
        var contact = this.contactRepositoryPort
                .findByID(id)
                .map(CompanyContactApplicationMapper::toDomainModel)
                .orElseThrow(() -> new CompanyContactNotExistsException(String.format(
                        "Контакта с идентификатором '%s' не существует",
                        id)));

        var individual = this.individualContactGateway
                .getByID(contact.getIndividualContact().getId())
                .orElseThrow(() -> new IndividualContactNotFoundException(String.format(
                        "Не удалось найти физ. лицо с идентификатором '%s'",
                        contact.getIndividualContact().getId().getValue())));

        var status = CompanyContactStatusCode.getByCode(command.statusCode());
        contact.changeStatus(status);

        return this.contactRepositoryPort.updateByOrigin(
                CompanyContactApplicationMapper.fromDomainModel(contact, individual)
        );
    }
}
