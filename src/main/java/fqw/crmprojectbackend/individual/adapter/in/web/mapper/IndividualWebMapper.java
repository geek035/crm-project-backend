package fqw.crmprojectbackend.individual.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddDTO;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualQueryDTO;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualUpdateDTO;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.command.IndividualUpdateCommand;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;

import java.util.UUID;

public class IndividualWebMapper {
    private IndividualWebMapper() {
    }

    public static IndividualAddCommand toApplicationModel(IndividualAddDTO from) {
        return new IndividualAddCommand(
                from.firstName(),
                from.secondName(),
                from.surname(),
                from.email(),
                from.phoneNumber(),
                from.birthdate());
    }

    public static IndividualByParamsQuery toApplicationModel(IndividualQueryDTO from) {
        var sort = from.sort();
        var pageNumber = from.pageNumber();
        var pageSize = from.pageSize();
        var filters = from.filters().stream()
                .map(it -> new FilterCriterion(
                        it.field(),
                        it.value(),
                        FilterCriterionMatchMode.getByMatchMode(it.matchMode())))
                .toList();


        return new IndividualByParamsQuery(pageSize, pageNumber, sort, filters);
    }

    public static IndividualUpdateCommand toApplicationModal(UUID id, IndividualUpdateDTO dto) {
        return new IndividualUpdateCommand(
                id,
                dto.firstName(),
                dto.secondName(),
                dto.surname(),
                dto.email(),
                dto.phoneNumber(),
                dto.birthdate());
    }
}
