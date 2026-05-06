package fqw.crmprojectbackend.company.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;
import fqw.crmprojectbackend.company.adapter.out.persistence.entity.contact.CompanyContactJPAEntity;
import fqw.crmprojectbackend.company.application.dto.CompanyContactDTO;
import fqw.crmprojectbackend.company.application.dto.IndividualContactDTO;
import fqw.crmprojectbackend.company.domain.model.contact.individual.IndividualContact;

public class CompanyContactPersistentMapper {
    public static CompanyContactDTO fromEntity(
            CompanyContactJPAEntity entity,
            IndividualContact individual) {
        return new CompanyContactDTO(
                entity.getId(),
                new IndividualContactDTO(
                        individual.getId().getValue(),
                        individual.getFullName().firstName(),
                        individual.getFullName().secondName(),
                        individual.getFullName().surname(),
                        individual.getEmail().value(),
                        individual.getPhoneNumber().value()),
                new DirectoryEntryDTO(
                        entity.getRole().getCode(),
                        entity.getRole().getDescription()
                ),
                new DirectoryEntryDTO(
                        entity.getStatus().getCode(),
                        entity.getStatus().getDescription()
                )
        );
    }
}
