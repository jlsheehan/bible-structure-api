package bible.exceptions;

public class BadReferenceException extends BibleStructureAPIException {
    public BadReferenceException() {
    }

    public BadReferenceException(String message) {
        super(message);
    }

    public BadReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadReferenceException(Throwable cause) {
        super(cause);
    }

    public BadReferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
