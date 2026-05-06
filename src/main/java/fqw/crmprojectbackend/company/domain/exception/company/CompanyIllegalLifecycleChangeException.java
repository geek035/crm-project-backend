package fqw.crmprojectbackend.company.domain.exception.company;

public class CompanyIllegalLifecycleChangeException extends RuntimeException {
    public CompanyIllegalLifecycleChangeException(String message) {
        super(message);
    }
}
