package bible.exceptions;

public class ReferenceNotFoundException extends BibleStructureAPIException {
    public ReferenceNotFoundException() {
    }

    public ReferenceNotFoundException(String message) {
        super(message);
    }

    public ReferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReferenceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
