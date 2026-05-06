package fqw.crmprojectbackend.company.adapter.in.web.mapper;

import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactAddDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactUpdateRoleDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.contact.CompanyContactUpdateStatusDTO;
import fqw.crmprojectbackend.company.application.command.CompanyContactAddCommand;
import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateRoleCommand;
import fqw.crmprojectbackend.company.application.command.CompanyContactUpdateStatusCommand;

import java.util.UUID;

public class CompanyContactWebMapper {
    public static CompanyContactAddCommand toCommand(CompanyContactAddDTO dto) {
        return new CompanyContactAddCommand(
                dto.individualID(),
                dto.roleCode());
    }

    public static CompanyContactUpdateRoleCommand toCommand(CompanyContactUpdateRoleDTO dto) {
        return new CompanyContactUpdateRoleCommand(
                dto.roleCode());
    }

    public static CompanyContactUpdateStatusCommand toCommand(CompanyContactUpdateStatusDTO dto) {
        return new CompanyContactUpdateStatusCommand(
                dto.statusCode());
    }
}
