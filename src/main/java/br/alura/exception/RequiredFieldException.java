package br.alura.exception;

public class RequiredFieldException extends RuntimeException {

    private final String message;

    public RequiredFieldException(final String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return super.getMessage();
        }
        return message;
    }
}
