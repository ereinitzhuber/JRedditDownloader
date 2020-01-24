package Exceptions;

public class EmptyResponseException extends RuntimeException {
    String message;
    public EmptyResponseException() {
    }
    public EmptyResponseException(String message) {
        super(message);
    }
    public EmptyResponseException(Throwable cause) {
        super(cause);
    }
    public EmptyResponseException(String message, Throwable throwable) {
        super(message, throwable);
    }
    @Override
    public String toString() {
        return "EmptyResponseException: Pushshift response empty, no links found.";
    }
}
