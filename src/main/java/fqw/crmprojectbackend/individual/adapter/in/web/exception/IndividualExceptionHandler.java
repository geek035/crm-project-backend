package fqw.crmprojectbackend.individual.adapter.in.web.exception;

import fqw.crmprojectbackend.common.web.exception.HTTPErrorCode;
import fqw.crmprojectbackend.common.web.exception.WebError;
import fqw.crmprojectbackend.individual.adapter.in.web.IndividualController;
import fqw.crmprojectbackend.individual.domain.exception.IndividualDuplicateEmailException;
import fqw.crmprojectbackend.individual.domain.exception.IndividualInvalidEmailException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = IndividualController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IndividualExceptionHandler {

    @ExceptionHandler(IndividualDuplicateEmailException.class)
    public ResponseEntity<WebError> handleIndividualDuplicateEmailException(
            IndividualDuplicateEmailException exception) {
        var error = new WebError(
                HTTPErrorCode.CONFLICT.getStatus().value(),
                HTTPErrorCode.CONFLICT.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.CONFLICT.getStatus()).body(error);
    }

    @ExceptionHandler(IndividualInvalidEmailException.class)
    public ResponseEntity<WebError> handleIndividualInvalidEmailException(
            IndividualInvalidEmailException exception) {
        var error = new WebError(
                HTTPErrorCode.VALIDATION_ERROR.getStatus().value(),
                HTTPErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HTTPErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }
}
