package fqw.crmprojectbackend.common.web.exception;

import lombok.Getter;

public class GeneralAPIException extends RuntimeException {
    @Getter
    private final APIErrorCode code;

    public GeneralAPIException(APIErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
