package bible;

import bible.exceptions.BadReferenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReferenceBuilder {

    private BookName bookName;
    private Optional<Integer> chapter;
    private Optional<Integer> verse;
    private Optional<Integer> endChapter;
    private Optional<Integer> endVerse;

    ReferenceBuilder() {
        chapter = Optional.empty();
        verse = Optional.empty();
        endChapter = Optional.empty();
        endVerse = Optional.empty();
    }

    public List<Reference> build() {
        List<Reference> references = new ArrayList<>();
        if (chapter.isPresent() && verse.isPresent())
            references.add(new Reference(bookName, chapter.get(), verse.get()));
        else chapter.ifPresent(c -> references.add(new Reference(bookName, c)));
        if (endChapter.isPresent() && endVerse.isPresent())
            references.add(new Reference(bookName, endChapter.get(), endVerse.get()));
        else if (endVerse.isPresent() && !endChapter.isPresent())
            references.add(new Reference(bookName, chapter.get(), endVerse.get()));
        else endChapter.ifPresent(c -> references.add(new Reference(bookName, c)));
        return references;
    }

    public String format(ReferenceFormatter formatter) throws BadReferenceException {
        return formatter.format(build());
    }

    public ReferenceBuilder book(BookName bookName) {
        this.bookName = bookName;
        return this;
    }

    public ReferenceBuilder chapter(Integer chapter) {
        this.chapter = Optional.of(chapter);
        return this;
    }

    public ReferenceBuilder verse(Integer verse) {
        this.verse = Optional.of(verse);
        return this;
    }

    public ReferenceBuilder endChapter(Integer chapter) {
        this.endChapter = Optional.of(chapter);
        return this;
    }

    public ReferenceBuilder endVerse(Integer verse) {
        this.endVerse = Optional.of(verse);
        return this;
    }

    public String toString() {
        return bookName.name() + "(" +
                chapter.get() +
                ":" +
                (verse.isPresent() ? verse.get() : "NULL") +
                "-" +
                (endChapter.isPresent() ? endChapter.get() : "NULL") +
                ":" +
                (endVerse.isPresent() ? endVerse.get() : "NULL") + ")";
    }
}
