package bible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passage {

    private Citation citation;
    private Map<Integer,String> texts;

    public Passage(Citation citation, Map<Integer,String> texts) {
        this.citation = citation;
        this.texts = texts;
    }

    public Map<Integer,String> getTexts() {
        return texts;
    }

    public Integer getSize() {
        return texts.size();
    }

    public void add(Integer verseNumber, String text) {
        texts.put(verseNumber, text);
    }
}
