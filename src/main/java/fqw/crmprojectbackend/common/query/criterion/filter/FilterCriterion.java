package fqw.crmprojectbackend.common.query.criterion.filter;

import jakarta.validation.constraints.NotNull;

public record FilterCriterion(
        @NotNull
        String field,
        @NotNull
        Object value,
        @NotNull
        FilterCriterionMatchMode mode) {}
