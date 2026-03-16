package fqw.crmprojectbackend.common.model.web;

import java.util.List;

public record WebError(
        Integer status,
        String title,
        List<String> messages) {}
