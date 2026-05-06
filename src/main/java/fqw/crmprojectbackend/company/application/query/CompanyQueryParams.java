package fqw.crmprojectbackend.company.application.query;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.sort.SortCriterion;

import java.util.List;

public record CompanyQueryParams(
        int pageSize,
        int pageNumber,
        List<SortCriterion> sort,
        List<FilterCriterion> filters) {}
