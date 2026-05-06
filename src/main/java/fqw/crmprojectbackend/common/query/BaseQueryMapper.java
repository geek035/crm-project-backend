package fqw.crmprojectbackend.common.query;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;

public class BaseQueryMapper {
    public static BaseQueryParams toParams(BaseQueryDTO dto) {
        return new BaseQueryParams(
                dto.pageSize(),
                dto.pageNumber(),
                dto.sort(),
                dto.filters()
                        .stream()
                        .map(it -> new FilterCriterion(
                                it.field(),
                                it.value(),
                                FilterCriterionMatchMode.getByMatchMode(it.matchMode())
                        ))
                        .toList());
    }
}
