package fqw.crmprojectbackend.individual.adapter.in.web.request;

import fqw.crmprojectbackend.common.query.SortCriterion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record IndividualQueryRequest(
        @PositiveOrZero
        @NotNull
        Integer pageNumber,
        @Positive
        @NotNull
        Integer pageSize,
        List<SortCriterion> sort,
        List<IndividualQueryFilter> filters) {}
