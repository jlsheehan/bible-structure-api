package bible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Citation {

    private BookName bookName;
    private Optional<Integer> startChapter;
    private Optional<Integer> startVerse;
    private Optional<Integer> endChapter;
    private Optional<Integer> endVerse;

//    Citation() {
//        startChapter = Optional.empty();
//        startVerse = Optional.empty();
//        endChapter = Optional.empty();
//        endVerse = Optional.empty();
//    }

    Citation(BookName bookName, Optional<Integer> startChapter, Optional<Integer> startVerse, Optional<Integer> endChapter, Optional<Integer> endVerse) {
        this.bookName = bookName;
        this.startChapter = startChapter;
        this.startVerse = startVerse;
        this.endChapter = endChapter;
        this.endVerse = endVerse;
    }

    public BookName getBookName() {
        return bookName;
    }

    public Optional<Integer> getStartChapter() {
        return startChapter;
    }

    public Optional<Integer> getStartVerse() {
        return startVerse;
    }

    public Optional<Integer> getEndChapter() {
        return endChapter;
    }

    public Optional<Integer> getEndVerse() {
        return endVerse;
    }

    public String toText() throws CitationIncompleteException {
        if (bookName == null) throw new CitationIncompleteException();
        else {
            if (endVerse.isPresent() && endChapter.isPresent() && startVerse.isPresent() && startChapter.isPresent())
                return citation(bookName.getAbbreviation(), startChapter.get(), startVerse.get(), endChapter.get(), endVerse.get());
            else if (startChapter.isPresent() && endChapter.isPresent())
                return citationChaptersOnly(bookName.getAbbreviation(), startChapter.get(), endChapter.get());
            else if (startChapter.isPresent() && startVerse.isPresent())
                return citationSingleVerse(bookName.getAbbreviation(), startChapter.get(), startVerse.get());
            else if (startChapter.isPresent()) return citationSingleChapter(bookName.getAbbreviation(), startChapter.get());
            else if (!startChapter.isPresent() && !startVerse.isPresent() && !endChapter.isPresent() && !endVerse.isPresent())
                return citationBookOnly(bookName.getAbbreviation());
            else throw new CitationIncompleteException();
        }
    }

    private static String citationSingleChapter(String abbreviation, Integer chapter) {
        return String.format("%s. %d", abbreviation, chapter);
    }

    private static String citationBookOnly(String abbreviation) {
        return String.format("%s.", abbreviation);
    }

    private static String citationSingleVerse(String abbreviation, Integer chapter, Integer verse) {
        return String.format("%s. %d:%d", abbreviation, chapter, verse);
    }

    private static String citationChaptersOnly(String abbreviation, Integer chapter1, Integer chapter2) {
        return String.format("%s. %d-%d", abbreviation, chapter1, chapter2);
    }

    private static String citation(String abbreviation, Integer chapter1, Integer verse1, Integer chapter2, Integer verse2) {
        if (chapter1 == chapter2) return String.format("%s. %d:%d-%d", abbreviation, chapter1, verse1, verse2);
        else return String.format("%s. %d:%d-%d:%d", abbreviation, chapter1, verse1, chapter2, verse2);
    }

    public static CitationBuilder newCitation() {
        return new CitationBuilder();
    }
}
