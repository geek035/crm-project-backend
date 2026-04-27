package fqw.crmprojectbackend.company.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyAddDTO;
import fqw.crmprojectbackend.company.adapter.in.web.request.CompanyQueryDTO;
import fqw.crmprojectbackend.company.application.command.CompanyAddCommand;
import fqw.crmprojectbackend.company.application.query.CompanyQueryParams;

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
