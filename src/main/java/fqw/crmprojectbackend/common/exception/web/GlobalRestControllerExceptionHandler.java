package fqw.crmprojectbackend.common.exception.web;

import fqw.crmprojectbackend.common.model.web.WebError;
import jakarta.validation.constraints.NotNull;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(GeneralAPIException.class)
    public ResponseEntity<WebError> handleGeneralAPIException(
            @NotNull GeneralAPIException exception) {
        var error = new WebError(
                exception.getCode().getStatus().value(),
                exception.getCode().getTitle(),
                List.of(exception.getMessage()));

        return ResponseEntity.status(exception.getCode().getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<WebError> handleMethodArgumentTypeMismatchException(
            @NotNull MethodArgumentTypeMismatchException exception) {
        var error = new WebError(
                HttpStatus.BAD_REQUEST.value(),
                "Ошибка преобразования аргумента",
                List.of(exception.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebError> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        var messages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var error = new WebError(
                APIErrorCode.VALIDATION_ERROR.getStatus().value(),
                APIErrorCode.VALIDATION_ERROR.getTitle(),
                messages);

        return ResponseEntity.status(APIErrorCode.VALIDATION_ERROR.getStatus()).body(error);
    }
}
