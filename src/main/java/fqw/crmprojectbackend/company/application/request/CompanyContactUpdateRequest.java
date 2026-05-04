package fqw.crmprojectbackend.company.application.request;

public record CompanyContactUpdateRequest(
        String roleCode,
        String statusCode
) {}
