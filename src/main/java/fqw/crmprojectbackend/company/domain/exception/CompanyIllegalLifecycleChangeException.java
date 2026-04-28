package fqw.crmprojectbackend.company.domain.exception;

public class CompanyIllegalLifecycleChangeException extends RuntimeException {
    public CompanyIllegalLifecycleChangeException(String message) {
        super(message);
    }
}
