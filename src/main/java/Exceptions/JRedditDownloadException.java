package Exceptions;

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
