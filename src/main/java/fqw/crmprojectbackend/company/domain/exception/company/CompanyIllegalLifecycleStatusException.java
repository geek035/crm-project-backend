package fqw.crmprojectbackend.company.domain.exception.company;

public class CompanyIllegalLifecycleStatusException extends RuntimeException {
    public CompanyIllegalLifecycleStatusException(String message) {
        super(message);
    }
}
