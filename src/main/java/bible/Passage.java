package bible;

import java.util.ArrayList;
import java.util.List;

public class Passage {

    private List<Verse> verses;

    public Passage() {
        verses = new ArrayList<>();
    }

    public Passage(List<Verse> verses) {
        this();
        this.verses.addAll(verses);
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public Integer getSize() {
        return verses.size();
    }

    public void add(Verse verse) {
        verses.add(verse);
    }
}
