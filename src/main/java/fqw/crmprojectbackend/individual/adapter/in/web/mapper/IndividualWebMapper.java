package fqw.crmprojectbackend.individual.adapter.in.web.mapper;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualAddRequest;
import fqw.crmprojectbackend.individual.adapter.in.web.request.IndividualQueryRequest;
import fqw.crmprojectbackend.individual.adapter.in.web.response.IndividualDTO;
import fqw.crmprojectbackend.individual.application.command.IndividualAddCommand;
import fqw.crmprojectbackend.individual.application.query.IndividualByParamsQuery;
import fqw.crmprojectbackend.individual.application.response.IndividualResponse;

public class IndividualWebMapper {
    private IndividualWebMapper() {
    }

    public static IndividualDTO toDTO(IndividualResponse from) {
        return new IndividualDTO(
                from.id(),
                from.firstName(),
                from.secondName(),
                from.surname(),
                from.email(),
                from.phoneNumber(),
                from.birthdate()
        );
    }

    public static IndividualAddCommand toApplicationModel(IndividualAddRequest from) {
        return new IndividualAddCommand(
                from.firstName(),
                from.secondName(),
                from.surname(),
                from.email(),
                from.phoneNumber(),
                from.birthdate());
    }

    public static IndividualByParamsQuery toApplicationModel(IndividualQueryRequest from) {
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
}
