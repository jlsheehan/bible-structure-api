package bible;

import bible.exceptions.BadReferenceException;
import bible.exceptions.ReferenceIncompleteException;
import bible.exceptions.ReferenceParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitationTest {

    @Test
    public void testVerseCitation() throws ReferenceIncompleteException, BadReferenceException {
        String result = "Gen. 1:1-20:18";
        String citation = Reference.newReference()
                .book(BookName.GENESIS)
                .chapter(1)
                .verse(1)
                .endChapter(20)
                .endVerse(18)
                .format(new Citation());
        assertEquals(result, citation);
    }

    @Test
    public void testPassageCitation() throws ReferenceIncompleteException, BadReferenceException {
        String result = "Gen. 1:1-10";
        String citation = Reference.newReference()
                .book(BookName.GENESIS)
                .chapter(1)
                .verse(1)
                .endVerse(10)
                .format(new Citation());
        assertEquals(result, citation);
    }

    @Test
    public void parse() throws ReferenceParseException, ReferenceIncompleteException, BadReferenceException {
        String[] testStrings = new String[] {"John. 1", "John. 3:16", "1 Tim. 2-4", "1 Pet. 1:1-2:2", "Eccles. 1:1-4"};
        String[] resultStrings = new String[] {"John. 1", "John. 3:16", "1 Tim. 2-4", "1 Pet. 1:1-2:2", "Eccl. 1:1-4"};
        Citation citation = new Citation();
        for (int s = 0; s < testStrings.length; s++) {
            assertEquals(resultStrings[s], citation.parse(testStrings[s]).format(citation));
            System.out.println(String.format("Passed: %s -> %s", testStrings[s], resultStrings[s]));
        }
    }
}