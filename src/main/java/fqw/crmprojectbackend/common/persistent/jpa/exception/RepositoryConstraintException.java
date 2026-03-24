package fqw.crmprojectbackend.common.persistent.jpa.exception;

public class RepositoryConstraintException extends  RuntimeException {
    public RepositoryConstraintException(String message) {
        super(message);
    }
}
