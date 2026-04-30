package fqw.crmprojectbackend.company.domain.exception.contact;

public class CompanyContactDuplicateException extends RuntimeException {
    public CompanyContactDuplicateException(String message) {
        super(message);
    }
}
