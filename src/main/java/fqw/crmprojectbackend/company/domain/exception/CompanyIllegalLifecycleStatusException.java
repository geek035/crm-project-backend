package fqw.crmprojectbackend.company.domain.exception;

public class CompanyIllegalLifecycleStatusException extends RuntimeException {
    public CompanyIllegalLifecycleStatusException(String message) {
        super(message);
    }
}
