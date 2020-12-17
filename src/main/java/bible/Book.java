package bible;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Book {
    GENESIS("Genesis", new String[] {"Gen"}, 50),
    EXODUS("Exodus", new String[] {"Ex"}, 40),
    LEVITICUS("Leviticus", new String[] {"Lev"}, 27),
    NUMBERS("Numbers", new String[] {"Num"}, 36),
    DEUTERONOMY("Deuteronomy", new String[] {"Deut"}, 34),
    JOSHUA("Joshua", new String[] {"Josh"}, 24),
    JUDGES("Judges", new String[] {"Judg"}, 21),
    RUTH("Ruth", new String[] {"Ruth"}, 4),
    ONESAMUEL("1 Samuel", new String[] {"1 Sam"}, 31),
    TWOSAMUEL("2 Samuel", new String[] {"2 Sam"}, 24),
    ONEKINGS("1 Kings", new String[] {"1 Kin", "1 Kings"}, 22),
    TWOKINGS("2 Kings", new String[] {"2 Kin", "2 Kings"}, 25),
    ONECHRONICLES("1 Chronicles", new String[] {"1 Chr", "1 Chron"}, 29),
    TWOCHRONICLES("2 Chronicles", new String[] {"2 Chr", "2 Chron"}, 36),
    EZRA("Ezra", new String[] {"Ezra"}, 10),
    NEHEMIAH("Nehemiah", new String[] {"Neh"}, 13),
    ESTHER("Esther", new String[] {"Esth", "Est"}, 10),
    JOB("Job", new String[] {"Job"}, 42),
    PSALMS("Psalms", new String[] {"Ps"}, 150),
    PROVERBS("Proverbs", new String[] {"Prov"}, 31),
    ECCLESIASTES("Ecclesiastes", new String[] {"Eccl", "Eccles"}, 12),
    SONGOFSOLOMON("Song of Solomon", new String[] {"Song"}, 8),
    ISAIAH("Isaiah", new String[] {"Is", "Isa"}, 66),
    JEREMIAH("Jeremiah", new String[] {"Jer"}, 52),
    LAMENTATIONS("Lamentations", new String[] {"Lam"}, 5),
    EZEKIEL("Ezekiel", new String[] {"Ezek"}, 48),
    DANIEL("Daniel", new String[] {"Dan"}, 12),
    HOSEA("Hosea", new String[] {"Hos"}, 14),
    JOEL("Joel", new String[] {"Joel"}, 3),
    AMOS("Amos", new String[] {"Amos"}, 9),
    OBADIAH("Obadiah", new String[] {"Obad"}, 1),
    JONAH("Jonah", new String[] {"Jon", "Jonah"}, 4),
    MICAH("Micah", new String[] {"Mic"}, 7),
    NAHUM("Nahum", new String[] {"Nah"}, 3),
    HABAKKUK("Habakkuk", new String[] {"Hab"}, 3),
    ZEPHANIAH("Zephaniah", new String[] {"Zeph"}, 3),
    HAGGAI("Haggai", new String[] {"Hag"}, 2),
    ZECHARIAH("Zechariah", new String[] {"Zech"}, 14),
    MALACHI("Malachi", new String[] {"Mal"}, 4),
    MATTHEW("Matthew", new String[] {"Matt"}, 28),
    MARK("Mark", new String[] {"Mark"}, 16),
    LUKE("Luke", new String[] {"Luke"}, 24),
    JOHN("John", new String[] {"John"}, 21),
    ACTS("Acts", new String[] {"Acts"}, 28),
    ROMANS("Romans", new String[] {"Rom"}, 16),
    ONECORINTHIANS("1 Corinthians", new String[] {"1 Cor"}, 16),
    TWOCORINTHIANS("2 Corinthians", new String[] {"2 Cor"}, 13),
    GALATIANS("Galatians", new String[] {"Gal"}, 6),
    EPHESIANS("Ephesians", new String[] {"Eph"}, 6),
    PHILIPPIANS("Philippians", new String[] {"Phil"}, 4),
    COLOSSIANS("Colossians", new String[] {"Col"}, 4),
    ONETHESSALONIANS("1 Thessalonians", new String[] {"1 Thess"}, 5),
    TWOTHESSALONIANS("2 Thessalonians", new String[] {"2 Thess"}, 3),
    ONETIMOTHY("1 Timothy", new String[] {"1 Tim"}, 6),
    TWOTIMOTHY("2 Timothy", new String[] {"2 Tim"}, 4),
    TITUS("Titus", new String[] {"Titus"}, 3),
    PHILEMON("Philemon", new String[] {"Philem"}, 1),
    HEBREWS("Hebrews", new String[] {"Heb"}, 13),
    JAMES("James", new String[] {"James"}, 5),
    ONEPETER("1 Peter", new String[] {"1 Pet"}, 5),
    TWOPETER("2 Peter", new String[] {"2 Pet"}, 3),
    ONEJOHN("1 John", new String[] {"1 John"}, 5),
    TWOJOHN("2 John", new String[] {"2 John"}, 1),
    THREEJOHN("3 John", new String[] {"3 John"}, 1),
    JUDE("Jude", new String[] {"Jude"}, 1),
    REVELATION("Revelation", new String[] {"Rev"}, 22);

    private final String title;
    private final String[] abbreviations;
    private final Integer numChapters;

    Book(String title, String[] abbreviations, Integer numChapters) {
        this.title = title;
        this.abbreviations = abbreviations;
        this.numChapters = numChapters;
    }

    public String getTitle() {
        return title;
    }

    public String getAbbreviation() {
        return abbreviations[0];
    }

    public String[] getAbbreviations() {
        return abbreviations;
    }

    public Integer getNumChapters() {
        return numChapters;
    }

    public Boolean isValidCitation(Integer chapter) {
        return chapter <= numChapters;
    }

    public static Book valueOfAbbreviation(String abbreviationString) throws BookNotFoundException {
        return Arrays.stream(Book.values())
                .filter(b -> b.matches(abbreviationString.strip().toLowerCase()))
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }

    public Boolean matches(final String matchString) {
        return Stream.concat(Arrays.stream(abbreviations).map(String::toLowerCase), Stream.of(title.toLowerCase()))
                .anyMatch(a -> a.equals(matchString.toLowerCase()));
    }
}
