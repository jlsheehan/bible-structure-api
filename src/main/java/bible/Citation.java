package bible;

import bible.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Citation implements ReferenceFormatter {

    private static final String citationPattern = "(?<book>(?<abbrev>(?<prefix>[1-3][ ])?[A-Z][a-z]{1,5})[\\.])(?<chapVerse>[ ](?<lowRange>(?<startChapter>[1-9][0-9]*)([\\:](?<startVerse>[1-9][0-9]*))?)(?<highRange>(?:[-])(?<endChapter>[1-9][0-9]*)((?:[\\:])(?<endVerse>[1-9][0-9]*))?)?)?";
    private Logger logger = LoggerFactory.getLogger(Citation.class);

    @Override
    public String formatReference(Reference reference) throws ReferenceIncompleteException, ReferenceNotFoundException {
        if (reference.getVerse() == null) throw new ReferenceIncompleteException();
        else return String.format("%s. %d:%d", reference.getBookName().getAbbreviation(), reference.getChapter(), reference.getVerse());
    }

    @Override
    public String formatChapter(Reference reference) {
        return String.format("%s. %d", reference.getBookName().getAbbreviation(), reference.getChapter());
    }

    @Override
    public String formatBook(Reference reference) {
        return String.format("%s.", reference.getBookName().getAbbreviation());
    }

    @Override
    public String formatChapters(Reference reference, Reference endReference) throws BadReferenceException {
        if (reference.getBookName().equals(endReference.getBookName())) return String.format("%s. %d-%d", reference.getBookName().getAbbreviation(), reference.getChapter(), endReference.getChapter());
        else throw new BadReferenceException();
    }

    @Override
    public String formatPassage(Reference reference, Reference endReference) throws BadReferenceException {
        if (
                reference.getBookName().equals(endReference.getBookName()) &&
                reference.getChapter().equals(endReference.getChapter()) &&
                reference.getVerse() != null &&
                endReference.getVerse() != null) {
            logger.info("{}", endReference);
            return String.format("%s. %d:%d-%d", reference.getBookName().getAbbreviation(), reference.getChapter(), reference.getVerse(), endReference.getVerse());
        } else if (
                reference.getBookName().equals(endReference.getBookName()) &&
                reference.getVerse() != null &&
                endReference.getVerse() != null) {
            return String.format("%s. %d:%d-%d:%d", reference.getBookName().getAbbreviation(), reference.getChapter(), reference.getVerse(), endReference.getChapter(), endReference.getVerse());
        } else if (
                reference.getBookName().equals(endReference.getBookName()) &&
                reference.getVerse() == null &&
                endReference.getVerse() == null) {
            return String.format("%s. %d-%d", reference.getBookName().getAbbreviation(), reference.getChapter(), endReference.getChapter());
        } else throw new BadReferenceException();
    }

    @Override
    public ReferenceBuilder parse(String citationString) throws ReferenceParseException {
        ReferenceBuilder builder = new ReferenceBuilder();
        BookName bookName;
        Optional<Integer> startChapter= Optional.empty();;
        Optional<Integer> startVerse= Optional.empty();
        Optional<Integer> endChapter= Optional.empty();
        Optional<Integer> endVerse= Optional.empty();

        Pattern pattern = Pattern.compile(citationPattern);
        Matcher matcher = pattern.matcher(citationString);
        try {
            if (matcher.find()) {
                builder.book(BookName.valueOfAbbreviation(matcher.group("abbrev")));
                if (matcher.group("startChapter") != null)
                    builder.chapter(Integer.valueOf(matcher.group("startChapter")));
                if (matcher.group("startVerse") != null)
                    builder.verse(Integer.valueOf(matcher.group("startVerse")));
//                if (startChapter.isPresent() && startVerse.isPresent()) references.add(new Reference(bookName, startChapter.get(), startVerse.get()));
//                else if (startChapter.isPresent()) references.add(new Reference(bookName, startChapter.get()));
                if (matcher.group("startChapter") != null && matcher.group("startVerse") != null && matcher.group("endChapter") != null && matcher.group("endVerse") == null) {
                    // we have caught endVerse in endChapter, to make it simple we will set endChapter to startChapter
                    builder.endChapter(Integer.valueOf(matcher.group("startChapter")));
                    builder.endVerse(Integer.valueOf(matcher.group("endChapter")));
                } else if (matcher.group("endChapter") != null && (matcher.group("endVerse") != null)) {
                    builder.endChapter(Integer.valueOf(matcher.group("endChapter")));
                    builder.endVerse(Integer.valueOf(matcher.group("endVerse")));
                } else if (matcher.group("endChapter") != null)
                    builder.endChapter(Integer.valueOf(matcher.group("endChapter")));
                logger.warn("Parsed {} to builder {}", citationString, builder);
                return builder;
            } else throw new ReferenceParseException("No citiation found :(");
        } catch (BookNotFoundException e) {
            throw new ReferenceParseException(e);
        }
    }


}
