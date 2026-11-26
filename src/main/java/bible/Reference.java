package bible;

public class Reference implements Comparable<Reference> {

    private final BookName bookName;
    private final Integer chapter;
    private final Integer verse;

    Reference(BookName bookName, Integer chapter) {
        this.bookName = bookName;
        this.chapter = chapter;
        this.verse = null;
    }

    Reference(BookName bookName, Integer chapter, Integer verse) {
        this.bookName = bookName;
        this.chapter = chapter;
        this.verse = verse;
    }

    public BookName getBookName() {
        return bookName;
    }

    public Integer getChapter() {
        return chapter;
    }

    public Integer getVerse() {
        return verse;
    }

    @Override
    public int compareTo(Reference o) {
        if (bookName.equals(o.getBookName())) {
            if (chapter.equals(o.getChapter())) return verse.compareTo(o.getVerse());
            else return chapter.compareTo(o.getChapter());
        } return bookName.compareTo(o.bookName);
    }

    @Override
    public String toString() {
        return bookName.getAbbreviation() + chapter.toString() + ":" + verse.toString();
    }

    public static ReferenceBuilder newReference() {
        return new ReferenceBuilder();
    }
}
