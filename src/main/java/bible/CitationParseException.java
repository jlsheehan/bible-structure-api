package bible;

public class CitationParseException extends BibleStructureAPIException {
    public CitationParseException() {
    }

    public CitationParseException(String message) {
        super(message);
    }

    public CitationParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CitationParseException(Throwable cause) {
        super(cause);
    }

    public CitationParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
