package fqw.crmprojectbackend.deal.application.query;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.sort.SortCriterion;

import java.util.List;

public record DealQueryParams(
        int pageSize,
        int pageNumber,
        List<SortCriterion> sort,
        List<FilterCriterion> filters
) {
}
