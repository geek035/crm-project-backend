package fqw.crmprojectbackend.company.application.request;

import fqw.crmprojectbackend.company.domain.model.adress.RegisteredAddress;
import fqw.crmprojectbackend.company.domain.model.company.*;

public record CompanyUpdateRequest(
        CompanyOfficialName officialName,
        CompanyCommercialName commercialName,
        CompanyINN inn,
        CompanyKPP kpp,
        CompanyClientSegment clientSegment,
        CompanyLifecycleStatus lifecycleStatus,
        RegisteredAddress registeredAddress
) {
}
