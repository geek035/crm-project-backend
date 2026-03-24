package fqw.crmprojectbackend.common.persistent.jpa.spectification;

import fqw.crmprojectbackend.common.query.FilterCriterionMatchMode;
import fqw.crmprojectbackend.common.query.FilterCriterion;
import fqw.crmprojectbackend.common.query.exception.UnknowQueryPropertyException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class FilterSpecificationBuilder<T> {

    private final List<FilterCriterion> filters = new ArrayList<>();

    public final FilterSpecificationBuilder<T> with(List<FilterCriterion> models) {
        this.filters.addAll(models);

        return this;
    }

    public final FilterSpecificationBuilder<T> with(FilterCriterion model) {
        this.filters.add(model);

        return this;
    }

    public final FilterSpecificationBuilder<T> with(
            String field,
            Object value,
            String mode) {
        var matchMode = FilterCriterionMatchMode.getByMatchMode(mode);
        var filterModel = new FilterCriterion(field, value, matchMode);
        filters.add(filterModel);

        return this;
    }

    public Specification<T> build() {
        if (filters.isEmpty()) return Specification.unrestricted();

        Specification<T> specification = new FilterSpecification(filters.getFirst());

        for (int i = 1; i < filters.size(); i++) {
            specification = specification.and(new FilterSpecification(filters.get(i)));
        }

        return specification;
    }

    @RequiredArgsConstructor
    private class FilterSpecification implements Specification<T> {

        private final FilterCriterion filter;

        @Override
        public Predicate toPredicate(
                @NonNull Root<T> root,
                @NonNull CriteriaQuery<?> query,
                @NonNull CriteriaBuilder criteriaBuilder) {

            final String containsFormat = String.format("%%%s%%", filter.value().toString());

            try {
                return switch (this.filter.mode()) {
                    case FilterCriterionMatchMode.STARTS_WITH -> criteriaBuilder.like(
                            root.get(filter.field()), String.format("%s%%", filter.value()));

                    case FilterCriterionMatchMode.CONTAINS -> criteriaBuilder.like(
                            root.get(filter.field()), containsFormat);

                    case FilterCriterionMatchMode.NOT_CONTAINS -> criteriaBuilder.notLike(
                            root.get(filter.field()), containsFormat);

                    case FilterCriterionMatchMode.ENDS_WITH -> criteriaBuilder.like(
                            root.get(filter.field()), String.format("%%%s", filter.value()));

                    case FilterCriterionMatchMode.EQUALS -> criteriaBuilder.equal(
                            root.get(filter.field()), filter.value());

                    case FilterCriterionMatchMode.NOT_EQUALS -> criteriaBuilder.notEqual(
                            root.get(filter.field()), filter.value());
                };
            } catch (IllegalArgumentException exception) {
                throw new UnknowQueryPropertyException(
                        String.format(
                                "Неизвестное поле '%s' запрашиваемого объекта",
                                this.filter.field()));
            }
        }
    }
}
