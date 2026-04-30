package fqw.crmprojectbackend.company.domain.exception.contact;

public class CompanyContactNotExistsException extends RuntimeException {
    public CompanyContactNotExistsException(String message) {
        super(message);
    }
}
