package fqw.crmprojectbackend.common.persistent.jpa.spectification;

import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterionMatchMode;
import fqw.crmprojectbackend.common.query.criterion.filter.FilterCriterion;
import fqw.crmprojectbackend.common.query.UnknowQueryPropertyException;
import jakarta.persistence.criteria.*;
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

    private Path<?> resolvePath(Root<T> root, String field) {
        if (!field.contains(".")) {
            return root.get(field);
        }

        String[] parts = field.split("\\.");
        From<?, ?> from = root;

        for (int i = 0; i < parts.length - 1; i++) {
            from = from.join(parts[i]);
        }

        return from.get(parts[parts.length - 1]);
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
            var path = resolvePath(root, filter.field());

            try {
                return switch (this.filter.mode()) {
                    case STARTS_WITH -> criteriaBuilder.like(
                            path.as(String.class),
                            String.format("%s%%", filter.value()));

                    case CONTAINS -> criteriaBuilder.like(
                            path.as(String.class),
                            containsFormat);

                    case NOT_CONTAINS -> criteriaBuilder.notLike(
                            path.as(String.class),
                            containsFormat);

                    case ENDS_WITH -> criteriaBuilder.like(
                            path.as(String.class),
                            String.format("%%%s", filter.value()));

                    case EQUALS -> criteriaBuilder.equal(
                            path,
                            filter.value());

                    case NOT_EQUALS -> criteriaBuilder.notEqual(
                            path,
                            filter.value());
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
