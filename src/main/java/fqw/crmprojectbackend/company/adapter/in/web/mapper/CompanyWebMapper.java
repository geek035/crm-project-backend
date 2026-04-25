package fqw.crmprojectbackend.company.adapter.in.web.mapper;

import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyAddDTO;
import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;

public class CompanyWebMapper {
    public static CompanyAddCommand toCommand(CompanyAddDTO dto) {
        return new CompanyAddCommand(
                dto.officialName(),
                dto.commercialName(),
                dto.inn(),
                dto.kpp(),
                dto.clientSegmentCode(),
                dto.registeredAddress());
    }
}
