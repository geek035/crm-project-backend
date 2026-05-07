package fqw.crmprojectbackend.deal.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.BaseQueryDTO;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealChangeStageDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealChangeStatusDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.adapter.in.web.request.DealUpdateDTO;
import fqw.crmprojectbackend.deal.application.command.DealChangeStageCommand;
import fqw.crmprojectbackend.deal.application.command.DealChangeStatusCommand;
import fqw.crmprojectbackend.deal.application.command.DealCreateCommand;
import fqw.crmprojectbackend.deal.application.command.DealUpdateCommand;
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

    public static DealUpdateCommand toCommand(DealUpdateDTO dto) {
        return new DealUpdateCommand(
                dto.title(),
                dto.description(),
                dto.priorityCode(),
                dto.sourceCode(),
                dto.expectedCloseDate());
    }

    public static DealChangeStageCommand toCommand(DealChangeStageDTO dto) {
        return new DealChangeStageCommand(
                dto.stageCode(),
                dto.closeInfo());
    }

    public static DealChangeStatusCommand toCommand(DealChangeStatusDTO dto) {
        return new DealChangeStatusCommand(
                dto.statusCode(),
                dto.closeInfo());
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
