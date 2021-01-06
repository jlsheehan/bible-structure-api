package bible;

public class CitationNotFoundException extends Exception {
    public CitationNotFoundException() {
    }

    public CitationNotFoundException(String message) {
        super(message);
    }

    public CitationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CitationNotFoundException(Throwable cause) {
        super(cause);
    }

    public CitationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
