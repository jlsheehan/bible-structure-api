package bible.exceptions;

public class ReferenceIncompleteException extends BibleStructureAPIException {
    public ReferenceIncompleteException() {
    }

    public ReferenceIncompleteException(String message) {
        super(message);
    }

    public ReferenceIncompleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceIncompleteException(Throwable cause) {
        super(cause);
    }

    public ReferenceIncompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
