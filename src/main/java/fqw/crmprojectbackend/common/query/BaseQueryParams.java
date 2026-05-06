package fqw.crmprojectbackend.common.query;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.sort.SortCriterion;

import java.util.List;

public record BaseQueryParams(
        int pageSize,
        int pageNumber,
        List<SortCriterion> sort,
        List<FilterCriterion> filters
) {
}
