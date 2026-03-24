package fqw.crmprojectbackend.individual.adapter.in.web.request;

public record IndividualQueryFilter(
        String field,
        Object value,
        String matchMode) {}
