package Exceptions;

/**
 * Generalized Exception I have more future plans for. As of now
 * it is only thrown in response to a Pushshift error in the main download method.
 */
public class JRedditDownloadException extends Exception {
    String message;
    public JRedditDownloadException() {
    }
    public JRedditDownloadException(String message) {
        super(message);
    }
    public JRedditDownloadException(Throwable cause) {
        super(cause);
    }
    public JRedditDownloadException(String message, Throwable throwable) {
        super(message, throwable);
    }
    @Override
    public String toString() {
        return "Something has gone wrong... Message:" + message;
    }
}
