package bible;

import bible.exceptions.BadReferenceException;

import java.util.SortedMap;
import java.util.TreeMap;

public class Passage {

    private BookName bookName;
    private SortedMap<Reference,String> texts;

    public Passage(BookName bookName) {
        this.bookName = bookName;
        texts = new TreeMap<>();
    }

    public Integer getSize() {
        return texts.size();
    }

    public void add(Integer chapter, Integer verse, String text) {
        texts.put(new Reference(bookName, chapter, verse), text);
    }

    public String format(ReferenceFormatter formatter) throws BadReferenceException {
        return formatter.formatPassage(texts.firstKey(), texts.lastKey());
    }
}
