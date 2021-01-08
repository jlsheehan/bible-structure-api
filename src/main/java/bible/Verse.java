package bible;

public class Verse {

    private Citation citation;
    private String text;

    public Verse() {}

    public Verse(Citation citation, String text) {
        this.citation = citation;
        this.text = text;
    }

    public Citation getCitation() {
        return citation;
    }

    public String getText() {
        return text;
    }
}
