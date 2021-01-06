package bible;

public interface BibleClient {

    Passage getPassage(Citation citation) throws CitationNotFoundException;
    Chapter getChapter(Citation citation) throws CitationNotFoundException;
    Verse getVerse(Citation citation) throws CitationNotFoundException;
}
