package bible;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CitationTest {

    @Test
    public void build() throws CitationIncompleteException {
        String result = "Gen. 1:1-20:18";
        Citation citation = Citation.newCitation()
                .book(Book.GENESIS)
                .startChapter(1)
                .startVerse(1)
                .endChapter(20)
                .endVerse(18);
        assertEquals(result, citation.build());
    }

    @Test
    public void parse() throws CitationParseException, CitationIncompleteException {
        String[] testStrings = new String[] {"John. 1", "John. 3:16", "1 Tim. 2-4", "1 Pet. 1:1-2:2", "Eccles. 1:1-4", "1 Kings."};
        String[] resultStrings = new String[] {"John. 1", "John. 3:16", "1 Tim. 2-4", "1 Pet. 1:1-2:2", "Eccl. 1:1-4", "1 Kin."};
        for (int s = 0; s < testStrings.length; s++) {
            Citation parsedCitation = Citation.parse(testStrings[s]);
            assertEquals(resultStrings[s], parsedCitation.build());
            System.out.println(String.format("Passed: %s -> %s", testStrings[s], resultStrings[s]));
        }
    }
}