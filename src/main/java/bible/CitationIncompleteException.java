package bible;

public class CitationIncompleteException extends BibleStructureAPIException {
    public CitationIncompleteException() {
    }

    public CitationIncompleteException(String message) {
        super(message);
    }

    public CitationIncompleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CitationIncompleteException(Throwable cause) {
        super(cause);
    }

    public CitationIncompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
