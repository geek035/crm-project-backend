package fqw.crmprojectbackend.company.adapter.in.web.mapper;

import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactAddDTO;
import fqw.crmprojectbackend.company.application.command.CompanyContactAddCommand;

import java.util.UUID;

public class CompanyContactWebMapper {
    public static CompanyContactAddCommand toCommand(CompanyContactAddDTO dto) {
        return new CompanyContactAddCommand(
                dto.individualID(),
                dto.roleCode());
    }
}
