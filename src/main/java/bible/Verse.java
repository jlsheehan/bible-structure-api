package bible;

public class Verse {

    private Reference reference;
    private String text;

    public Verse() {}

    public Verse(Reference reference, String text) {
        this.reference = reference;
        this.text = text;
    }

    public Reference getReference() {
        return reference;
    }

    public String getText() {
        return text;
    }
}
