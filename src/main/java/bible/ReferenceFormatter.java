package bible;

import bible.exceptions.*;

import java.util.List;

public interface ReferenceFormatter {

    default String format(Reference reference) throws BadReferenceException {
        try {
            return formatReference(reference);
        } catch (BibleStructureAPIException e) {
            try {
                return formatChapter(reference);
            } catch (BibleStructureAPIException e1) {
                try {
                    return formatChapter(reference);
                } catch (BibleStructureAPIException e2) {
                    try {
                        return formatBook(reference);
                    } catch (BibleStructureAPIException e3) {
                        throw new BadReferenceException();
                    }
                }
            }
        }
    }

    default String format(List<Reference> references) throws BadReferenceException {
        if (references.size() > 1) return formatPassage(references.get(0), references.get(references.size() - 1));
        else return format(references.get(0));
    }

    String formatReference(Reference reference) throws ReferenceIncompleteException, ReferenceNotFoundException, BadReferenceException;

    String formatChapter(Reference reference) throws ReferenceIncompleteException, ReferenceNotFoundException, BadReferenceException;

    String formatBook(Reference reference) throws ReferenceIncompleteException, ReferenceNotFoundException, BadReferenceException;

    String formatPassage(Reference reference, Reference endReference) throws BadReferenceException;

    String formatChapters(Reference reference, Reference endReference) throws BadReferenceException;

    ReferenceBuilder parse(String reference) throws ReferenceParseException;
}
