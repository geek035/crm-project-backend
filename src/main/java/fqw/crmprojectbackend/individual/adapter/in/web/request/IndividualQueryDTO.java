package fqw.crmprojectbackend.individual.adapter.in.web.request;

import fqw.crmprojectbackend.common.query.criterion.sort.SortCriterion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record IndividualQueryDTO(
        @PositiveOrZero
        @NotNull
        Integer pageNumber,
        @Positive
        @NotNull
        Integer pageSize,
        List<SortCriterion> sort,
        List<IndividualQueryFilterDTO> filters) {}
