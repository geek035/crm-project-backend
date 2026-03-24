package fqw.crmprojectbackend.common.web.exception;

import java.util.List;

public record WebError(
        Integer status,
        String title,
        List<String> messages) {}
