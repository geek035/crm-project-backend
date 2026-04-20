package fqw.crmprojectbackend.common.web.exception;

import lombok.Getter;

public class GeneralAPIException extends RuntimeException {
    @Getter
    private final HTTPErrorCode code;

    public GeneralAPIException(HTTPErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
