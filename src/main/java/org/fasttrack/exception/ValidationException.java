package org.fasttrack.exception;

public class ValidationException extends RuntimeException {

    private String validationError;

    public ValidationException(String validationError) {
        super(validationError);
        this.validationError = validationError;
    }

    // Getters and setters
    public String getValidationError() {
        return validationError;
    }

    public void setValidationError(String validationError) {
        this.validationError = validationError;
    }
}