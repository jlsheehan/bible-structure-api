package bible;

import java.util.SortedMap;

public class Passage {

    private Citation citation;
    private SortedMap<Integer,String> texts;

    public Passage(Citation citation, SortedMap<Integer,String> texts) {
        this.citation = citation;
        this.texts = texts;
    }

    public SortedMap<Integer,String> getTexts() {
        return texts;
    }

    public Integer getSize() {
        return texts.size();
    }

    public void add(Integer verseNumber, String text) {
        texts.put(verseNumber, text);
    }
}
