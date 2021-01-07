package bible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitationBuilder {

    private static final String citationPattern = "(?<book>(?<abbrev>(?<prefix>[1-3][ ])?[A-Z][a-z]{1,5})[\\.])(?<chapVerse>[ ](?<lowRange>(?<startChapter>[1-9][0-9]*)([\\:](?<startVerse>[1-9][0-9]*))?)(?<highRange>(?:[-])(?<endChapter>[1-9][0-9]*)((?:[\\:])(?<endVerse>[1-9][0-9]*))?)?)?";
    private static Logger staticLogger = LoggerFactory.getLogger(CitationBuilder.class);
    private BookName bookName;
    private Optional<Integer> startChapter;
    private Optional<Integer> startVerse;
    private Optional<Integer> endChapter;
    private Optional<Integer> endVerse;

    CitationBuilder() {
        startChapter = Optional.empty();
        startVerse = Optional.empty();
        endChapter = Optional.empty();
        endVerse = Optional.empty();
    }

    public Citation build() {
        return new Citation(bookName, startChapter, startVerse, endChapter, endVerse);
    }

//    private static String citationSingleChapter(String abbreviation, Integer chapter) {
//        return String.format("%s. %d", abbreviation, chapter);
//    }
//
//    private static String citationBookOnly(String abbreviation) {
//        return String.format("%s.", abbreviation);
//    }
//
//    private static String citationSingleVerse(String abbreviation, Integer chapter, Integer verse) {
//        return String.format("%s. %d:%d", abbreviation, chapter, verse);
//    }
//
//    private static String citationChaptersOnly(String abbreviation, Integer chapter1, Integer chapter2) {
//        return String.format("%s. %d-%d", abbreviation, chapter1, chapter2);
//    }
//
//    private static String citation(String abbreviation, Integer chapter1, Integer verse1, Integer chapter2, Integer verse2) {
//        if (chapter1 == chapter2) return String.format("%s. %d:%d-%d", abbreviation, chapter1, verse1, verse2);
//        else return String.format("%s. %d:%d-%d:%d", abbreviation, chapter1, verse1, chapter2, verse2);
//    }

    public static CitationBuilder parse(String citationString) throws CitationParseException {
        Pattern pattern = Pattern.compile(citationPattern);
        Matcher matcher = pattern.matcher(citationString);
        try {
            if (matcher.find()) {
                CitationBuilder builder = new CitationBuilder().book(BookName.valueOfAbbreviation(matcher.group("abbrev")));
                if (matcher.group("startChapter") != null)
                    builder.startChapter(Integer.valueOf(matcher.group("startChapter")));
                if (matcher.group("startVerse") != null)
                    builder.startVerse(Integer.valueOf(matcher.group("startVerse")));
                if (matcher.group("startChapter") != null && matcher.group("startVerse") != null && matcher.group("endChapter") != null && matcher.group("endVerse") == null) {
                    // we have caught endVerse in endChapter, to make it simple we will set endChapter to startChapter
                    builder.endChapter(Integer.valueOf(matcher.group("startChapter"))).endVerse(Integer.valueOf(matcher.group("endChapter")));
                } else if (matcher.group("endChapter") != null && (matcher.group("endVerse") != null)) {
                    builder.endChapter(Integer.valueOf(matcher.group("endChapter")));
                    builder.endVerse(Integer.valueOf(matcher.group("endVerse")));
                } else if (matcher.group("endChapter") != null)
                    builder.endChapter(Integer.valueOf(matcher.group("endChapter")));
                staticLogger.debug("Parsed {} to citation {}", citationString, builder);
                return builder;
            } else throw new CitationParseException("No citiation found :(");
        } catch (BookNotFoundException e) {
            throw new CitationParseException(e);
        }
    }

//    public static CitationBuilder newCitation() {
//        return new CitationBuilder();
//    }

    public CitationBuilder book(BookName bookName) {
        this.bookName = bookName;
        return this;
    }

    public CitationBuilder chapter(Integer chapter) {
        return startChapter(chapter);
    }

    public CitationBuilder verse(Integer verse) {
        return startVerse(verse);
    }

    public CitationBuilder startChapter(Integer chapter) {
        this.startChapter = Optional.of(chapter);
        return this;
    }

    public CitationBuilder endChapter(Integer chapter) {
        this.endChapter = Optional.of(chapter);
        return this;
    }

    public CitationBuilder startVerse(Integer verse) {
        this.startVerse = Optional.of(verse);
        return this;
    }

    public CitationBuilder endVerse(Integer verse) {
        this.endVerse = Optional.of(verse);
        return this;
    }

//    public String build() throws CitationIncompleteException {
//        if (bookName == null) throw new CitationIncompleteException();
//        else {
//            if (endVerse.isPresent() && endChapter.isPresent() && startVerse.isPresent() && startChapter.isPresent())
//                return citation(bookName.getAbbreviation(), startChapter.get(), startVerse.get(), endChapter.get(), endVerse.get());
//            else if (startChapter.isPresent() && endChapter.isPresent())
//                return citationChaptersOnly(bookName.getAbbreviation(), startChapter.get(), endChapter.get());
//            else if (startChapter.isPresent() && startVerse.isPresent())
//                return citationSingleVerse(bookName.getAbbreviation(), startChapter.get(), startVerse.get());
//            else if (startChapter.isPresent()) return citationSingleChapter(bookName.getAbbreviation(), startChapter.get());
//            else if (!startChapter.isPresent() && !startVerse.isPresent() && !endChapter.isPresent() && !endVerse.isPresent())
//                return citationBookOnly(bookName.getAbbreviation());
//            else throw new CitationIncompleteException();
//        }
//    }

    public Map<String, String> buildParamMap() throws CitationIncompleteException {
        Map<String, String> params = new HashMap<>();
        if (bookName == null) throw new CitationIncompleteException();
        else {
            params.put("book", bookName.getAbbreviation());
            if (endVerse.isPresent() && endChapter.isPresent() && startVerse.isPresent() && startChapter.isPresent()) {
                startChapter.ifPresent(c -> params.put("startChapter", c.toString()));
                startVerse.ifPresent(v -> params.put("startVerse", v.toString()));
                endChapter.ifPresent(c -> params.put("endChapter", c.toString()));
                endVerse.ifPresent(v -> params.put("endVerse", v.toString()));
            } else if (startChapter.isPresent() && endChapter.isPresent()) {
                startChapter.ifPresent(c -> params.put("startChapter", c.toString()));
                endChapter.ifPresent(c -> params.put("endChapter", c.toString()));
            } else if (startChapter.isPresent() && startVerse.isPresent()) {
                startChapter.ifPresent(c -> params.put("startChapter", c.toString()));
                startVerse.ifPresent(v -> params.put("startVerse", v.toString()));
            } else if (startChapter.isPresent()) {
                startChapter.ifPresent(c -> params.put("startChapter", c.toString()));
            } else throw new CitationIncompleteException();
            return params;
        }
    }
}
