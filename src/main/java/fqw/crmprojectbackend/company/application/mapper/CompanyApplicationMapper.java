package fqw.crmprojectbackend.company.application.mapper;

import fqw.crmprojectbackend.common.dto.DirectoryEntryDTO;
import fqw.crmprojectbackend.company.application.dto.CompanyDTO;
import fqw.crmprojectbackend.company.domain.model.company.*;

public class CompanyApplicationMapper {
    public static CompanyDTO fromDomainModel(Company company) {
        return new CompanyDTO(
                company.getId().getValue(),
                company.getOfficialName().value(),
                company.getCommercialName().value(),
                company.getInn().value(),
                company.getKpp().value(),
                new DirectoryEntryDTO(
                        company.getClientSegment().code().name(),
                        company.getClientSegment().code().getDescription()
                ),
                new DirectoryEntryDTO(
                        company.getLifecycleStatus().code().name(),
                        company.getLifecycleStatus().code().getDescription()
                ),
                RegisteredAddressApplicationMapper.fromDomainModel(company.getRegisteredAddress()));
    }

    public static Company toDomainModel(CompanyDTO dto) {
        return new Company(
                CompanyID.from(dto.id()),
                new CompanyOfficialName(dto.officialName()),
                new CompanyCommercialName(dto.commercialName()),
                new CompanyINN(dto.inn()),
                new CompanyKPP(dto.kpp()),
                new CompanyClientSegment(CompanyClientSegmentCode.getByCode(dto.clientSegment().code())),
                new CompanyLifecycleStatus(CompanyLifecycleStatusCode.getByCode(dto.lifecycleStatus().code())),
                RegisteredAddressApplicationMapper.toDomainModel(dto.registeredAddress())
        );
    }
}