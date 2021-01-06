package bible;

public interface BibleClient {

    String getText(Citation citation) throws CitationNotFoundException;
    String getText(String citation) throws CitationNotFoundException;
}
