package fqw.crmprojectbackend.individual.application.query;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.criterion.sort.SortCriterion;

import java.util.List;

public record IndividualByParamsQuery(
        Integer pageSize,
        Integer pageNumber,
        List<SortCriterion> sort,
        List<FilterCriterion> filters) {}
