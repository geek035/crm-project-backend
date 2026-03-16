package fqw.crmprojectbackend.individual.adapter.in.web.exception;

import fqw.crmprojectbackend.common.exception.web.APIErrorCode;
import fqw.crmprojectbackend.common.model.web.WebError;
import fqw.crmprojectbackend.individual.adapter.in.web.IndividualController;
import fqw.crmprojectbackend.individual.domain.exception.IndividualInvalidEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = IndividualController.class)
public class IndividualExceptionHandler {

    @ExceptionHandler(IndividualDuplicateEmailException.class)
    public ResponseEntity<WebError> handleIndividualDuplicateEmailException(
            IndividualDuplicateEmailException exception) {
        var error = new WebError(
                APIErrorCode.CONFLICT.getStatus().value(),
                APIErrorCode.CONFLICT.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(APIErrorCode.CONFLICT.getStatus()).body(error);
    }

    @ExceptionHandler(IndividualInvalidEmailException.class)
    public ResponseEntity<WebError> handleIndividualInvalidEmailException(
            IndividualInvalidEmailException exception) {
        var error = new WebError(
                APIErrorCode.VALIDATION_ERROR.getStatus().value(),
                APIErrorCode.VALIDATION_ERROR.getTitle(),
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(APIErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }
}
