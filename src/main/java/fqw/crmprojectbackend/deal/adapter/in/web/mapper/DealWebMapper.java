package fqw.crmprojectbackend.deal.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.BaseQueryDTO;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.application.command.DealCreateCommand;
import fqw.crmprojectbackend.deal.application.query.DealQueryParams;

public class DealWebMapper {
    public static DealCreateCommand toCommand(DealCreateDTO dto) {
        return new DealCreateCommand(
                dto.number(),
                dto.clientTypeCode(),
                dto.clientID(),
                dto.title(),
                dto.description(),
                dto.productCode(),
                dto.amount(),
                dto.currencyCode(),
                dto.priorityCode(),
                dto.sourceCode(),
                dto.expectedCloseDate());
    }

    public static DealQueryParams toQuery(BaseQueryDTO dto) {
        return new DealQueryParams(
                dto.pageSize(),
                dto.pageNumber(),
                dto.sort(),
                dto.filters().stream()
                        .map(it -> new FilterCriterion(
                                it.field(),
                                it.value(),
                                FilterCriterionMatchMode.getByMatchMode(it.matchMode())))
                        .toList());
    }
}
