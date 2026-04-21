package fqw.crmprojectbackend.common.query.criterion.filter;

public record FilterCriterion(
        String field,
        Object value,
        FilterCriterionMatchMode mode) {}
