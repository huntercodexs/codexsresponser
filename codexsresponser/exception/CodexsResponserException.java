package codexsresponser.exception;

import codexsresponser.settings.CodexsResponserSettings;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CodexsResponserException extends RuntimeException {
    public HttpStatus httpStatus;
    public int errorCode;
    public String message;

    public CodexsResponserException(
            CodexsResponserSettings.codexsResponserExpectedErrors codexsResponserExpectedErrors
    ) {
        this.httpStatus = codexsResponserExpectedErrors.getStatusCode();
        this.errorCode = codexsResponserExpectedErrors.getErrorCode();
        this.message = codexsResponserExpectedErrors.getMessage();
    }

    public CodexsResponserException(
            CodexsResponserSettings.codexsResponserExpectedErrors codexsResponserExpectedErrors,
            String message
    ) {
        this.httpStatus = codexsResponserExpectedErrors.getStatusCode();
        this.errorCode = codexsResponserExpectedErrors.getMessage() + codexsResponserExpectedErrors.getErrorCode();
        this.message = message;
    }
}
