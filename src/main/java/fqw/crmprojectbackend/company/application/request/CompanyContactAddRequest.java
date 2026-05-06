package fqw.crmprojectbackend.company.application.request;

import java.util.UUID;

public record CompanyContactAddRequest(
    UUID id,
    UUID companyID,
    UUID individualID,
    String roleCode,
    String statusCode) {
}
