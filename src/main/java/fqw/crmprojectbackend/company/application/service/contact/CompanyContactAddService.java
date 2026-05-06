package fqw.crmprojectbackend.company.application.service.contact;

import fqw.crmprojectbackend.company.application.command.CompanyContactAddCommand;
import fqw.crmprojectbackend.company.application.mapper.CompanyContactApplicationMapper;
import fqw.crmprojectbackend.company.application.port.in.CompanyContactAddUseCase;
import fqw.crmprojectbackend.company.application.port.out.CompanyContactRepositoryPort;
import fqw.crmprojectbackend.company.application.port.out.CompanyRepositoryPort;
import fqw.crmprojectbackend.company.application.port.out.IndividualContactGateway;
import fqw.crmprojectbackend.company.domain.exception.company.CompanyNotExistsException;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactDuplicateException;
import fqw.crmprojectbackend.company.domain.exception.contact.CompanyContactNotExistsException;
import fqw.crmprojectbackend.company.domain.model.company.CompanyID;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactRole;
import fqw.crmprojectbackend.company.domain.model.contact.CompanyContactRoleCode;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContactID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyContactAddService implements CompanyContactAddUseCase {
    private final IndividualContactGateway contactGateway;
    private final CompanyRepositoryPort companyRepositoryPort;
    private final CompanyContactRepositoryPort companyContactRepositoryPort;

    @Override
    @Transactional
    public UUID addContact(
            UUID companyID,
            CompanyContactAddCommand command) {
        var isExists = companyContactRepositoryPort.existByIndividualID(command.individualID());

        if (isExists) {
            throw new CompanyContactDuplicateException(String.format(
                    "Уже существует контакт, привязанный к физ. лицу с идентификатором '%s'",
                    command.individualID()));
        }

        var companyIDVO = CompanyID.from(companyID);
        var company = this.companyRepositoryPort.findByID(companyIDVO)
                .orElseThrow(() -> new CompanyNotExistsException(String.format(
                        "Компании с идентификатором '%s' не существует",
                        companyID)));

        var contactID = IndividualContactID.from(command.individualID());
        var individual = this.contactGateway
                .getByID(contactID)
                .orElseThrow(() -> new CompanyContactNotExistsException(String.format(
                        "Физ. лица с идентификатором '%s' не существует",
                        contactID.getValue())));

        var role = new CompanyContactRole(CompanyContactRoleCode
                .getByCode(command.roleCode()));

        var contact = company.addContact(individual, role);
        var request = CompanyContactApplicationMapper.toRequest(companyIDVO.getValue(), contact);

        return companyContactRepositoryPort.addContact(request);
    }
}
