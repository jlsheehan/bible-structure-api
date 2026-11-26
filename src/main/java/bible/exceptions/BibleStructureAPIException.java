package bible.exceptions;

public class BibleStructureAPIException extends Exception {
    public BibleStructureAPIException() {
    }

    public BibleStructureAPIException(String message) {
        super(message);
    }

    public BibleStructureAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public BibleStructureAPIException(Throwable cause) {
        super(cause);
    }

    public BibleStructureAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
