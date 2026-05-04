package fqw.crmprojectbackend.company.application.mapper;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.dto.IndividualContactDTO;
import fqw.crmprojectbackend.company.application.request.CompanyContactAddRequest;
import fqw.crmprojectbackend.company.domain.model.contact.*;
import fqw.crmprojectbackend.company.domain.model.contact.individual.*;

import java.util.UUID;

public class CompanyContactApplicationMapper {
    public static CompanyContact toDomainModel(CompanyContactDTO dto) {
        return new CompanyContact(
                CompanyContactID.from(dto.id()),
                new IndividualContact(
                        IndividualContactID.from(dto.individual().id()),
                        new IndividualContactFullName(
                                dto.individual().firstName(),
                                dto.individual().lastName(),
                                dto.individual().surname()),
                        new IndividualContactEmail(dto.individual().email()),
                        new IndividualContactPhoneNumber(dto.individual().phoneNumber())
                ),
                new CompanyContactRole(CompanyContactRoleCode.getByCode(dto.role().code())),
                new CompanyContactStatus(CompanyContactStatusCode.getByCode(dto.status().code()))
        );
    }

    public static CompanyContactDTO fromDomainModel(CompanyContact model, IndividualContact individual) {
        return new CompanyContactDTO(
                model.getId().getValue(),
                new IndividualContactDTO(
                        individual.getId().getValue(),
                        individual.getFullName().firstName(),
                        individual.getFullName().secondName(),
                        individual.getFullName().surname(),
                        individual.getEmail().value(),
                        individual.getPhoneNumber().value()),
                new DirectoryEntryDTO(
                        model.getRole().code().name(),
                        model.getRole().code().getDescription()),
                new DirectoryEntryDTO(
                        model.getStatus().code().name(),
                        model.getStatus().code().getDescription()));
    }

    public static CompanyContactAddRequest toRequest(UUID companyID, CompanyContact contact) {
        return new CompanyContactAddRequest(
                contact.getId().getValue(),
                companyID,
                contact.getIndividualContact().getId().getValue(),
                contact.getRole().code().name(),
                contact.getStatus().code().name());
    }
}
