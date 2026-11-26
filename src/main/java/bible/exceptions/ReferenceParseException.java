package bible.exceptions;

public class ReferenceParseException extends BibleStructureAPIException {
    public ReferenceParseException() {
    }

    public ReferenceParseException(String message) {
        super(message);
    }

    public ReferenceParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceParseException(Throwable cause) {
        super(cause);
    }

    public ReferenceParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
