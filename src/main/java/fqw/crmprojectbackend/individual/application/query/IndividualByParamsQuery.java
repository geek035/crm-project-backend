package fqw.crmprojectbackend.individual.application.query;

import fqw.crmprojectbackend.common.query.FilterCriterion;
import fqw.crmprojectbackend.common.query.SortCriterion;

import java.util.List;

public record IndividualByParamsQuery(
        Integer pageSize,
        Integer pageNumber,
        List<SortCriterion> sort,
        List<FilterCriterion> filters) {}
