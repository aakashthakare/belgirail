package in.at.exceptions;

import in.at.response.IRailErrorResponse;

public class IRailException extends RuntimeException {

    private final IRailErrorResponse errorResponse;

    public IRailErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public IRailException(IRailErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
