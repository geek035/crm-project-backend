package fqw.crmprojectbackend.common.query;

public record FilterCriterion(
        String field,
        Object value,
        FilterCriterionMatchMode mode) {}
