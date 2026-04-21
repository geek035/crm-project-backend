package fqw.crmprojectbackend.individual.adapter.in.web.request;

public record IndividualQueryFilterDTO(
        String field,
        Object value,
        String matchMode) {}
