package fqw.crmprojectbackend.company.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyAddDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyQueryDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyUpdateDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyUpdateLifecycleDTO;
import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;
import fqw.crmprojectbackend.company.application.command.CompanyUpdateCommand;
import fqw.crmprojectbackend.company.application.command.CompanyUpdateLifecycleCommand;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;

import java.util.UUID;

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

    public static CompanyUpdateCommand toCommand(CompanyUpdateDTO dto) {
        return new CompanyUpdateCommand(
                dto.officialName(),
                dto.commercialName(),
                dto.inn(),
                dto.kpp(),
                dto.clientSegmentCode(),
                dto.registeredAddress());
    }

    public static CompanyUpdateLifecycleCommand toCommand(CompanyUpdateLifecycleDTO dto) {
        return new CompanyUpdateLifecycleCommand(dto.lifecycleCode());
    }

    public static CompanyQueryParams toQuery(CompanyQueryDTO dto) {
        return new CompanyQueryParams(
                dto.pageSize(),
                dto.pageNumber(),
                dto.sort(),
                dto.filters().stream()
                        .map(it ->
                                new FilterCriterion(
                                        it.field(),
                                        it.value(),
                                        FilterCriterionMatchMode.getByMatchMode(it.matchMode())))
                        .toList()
        );
    }
}
