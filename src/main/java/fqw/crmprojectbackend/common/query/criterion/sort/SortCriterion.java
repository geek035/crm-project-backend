package fqw.crmprojectbackend.common.query.criterion.sort;

import jakarta.validation.constraints.NotNull;

public record SortCriterion(
        @NotNull
        String field,
        @NotNull
        SortCriterionDirection direction) {}
