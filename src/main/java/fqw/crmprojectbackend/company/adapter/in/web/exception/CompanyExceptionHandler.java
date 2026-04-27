package fqw.crmprojectbackend.company.adapter.in.web.exception;

import fqw.crmprojectbackend.common.web.exception.HTTPErrorCode;
import fqw.crmprojectbackend.common.web.exception.WebError;
import fqw.crmprojectbackend.company.adapter.in.web.CompanyController;
import fqw.crmprojectbackend.company.domain.exception.CompanyDuplicateINNException;
import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalClientSegmentException;
import fqw.crmprojectbackend.company.domain.exception.CompanyIllegalLifecycleStatusException;
import fqw.crmprojectbackend.company.domain.exception.CompanyNotExistsException;
import fqw.crmprojectbackend.individual.domain.exception.IndividualNotExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = CompanyController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CompanyExceptionHandler {

    @ExceptionHandler(CompanyDuplicateINNException.class)
    public ResponseEntity<WebError> handleCompanyDuplicateINNException(
            CompanyDuplicateINNException exception) {
        var error = new WebError(
                HTTPErrorCode.CONFLICT.getStatus().value(),
                HTTPErrorCode.CONFLICT.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.CONFLICT.getStatus()).body(error);
    }

    @ExceptionHandler({
            CompanyIllegalClientSegmentException.class,
            CompanyIllegalLifecycleStatusException.class,
    })
    public ResponseEntity<WebError> handleCompanyInvalidDataException(
            Exception exception) {
        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }

    @ExceptionHandler(CompanyNotExistsException.class)
    public ResponseEntity<WebError> handleCompanyNotExistsException(
            CompanyNotExistsException exception) {
        var error = new WebError(
                HTTPErrorCode.RESOURCE_NOT_FOUND.getStatus().value(),
                HTTPErrorCode.RESOURCE_NOT_FOUND.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.RESOURCE_NOT_FOUND.getStatus()).body(error);
    }
}
