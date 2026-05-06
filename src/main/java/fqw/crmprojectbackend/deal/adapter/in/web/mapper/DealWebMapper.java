package fqw.crmprojectbackend.deal.adapter.in.web.mapper;

import fqw.crmprojectbackend.deal.adapter.in.web.request.DealCreateDTO;
import fqw.crmprojectbackend.deal.application.command.DealCreateCommand;

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
}
