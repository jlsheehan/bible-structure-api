package bible;

import bible.exceptions.ReferenceNotFoundException;

public interface BibleClient {

    Passage getPassage(Citation citation) throws ReferenceNotFoundException;
    Chapter getChapter(Citation citation) throws ReferenceNotFoundException;
    Verse getVerse(Citation citation) throws ReferenceNotFoundException;
}
