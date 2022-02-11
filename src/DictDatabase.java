import java.util.HashMap;

/**
 * A "database" containing all the definitions.
 */
public enum DictDatabase {

    ARROW(new Definition(PartOfSpeech.NOUN, "Here is one arrow: <IMG> -=>> </IMG>")),

    BOOK(new Definition(PartOfSpeech.NOUN, "A set of pages."),
            new Definition(PartOfSpeech.NOUN,
                    "A written work published in printed or electronic form."),
            new Definition(PartOfSpeech.VERB, "To arrange for someone to have a seat on a plane."),
            new Definition(PartOfSpeech.VERB, "To arrange something on a particular date.")),

    DISTINCT(new Definition(PartOfSpeech.ADJECTIVE, "Familiar. Worked in Java."),
            new Definition(PartOfSpeech.ADJECTIVE,
                    "Unique. No duplicates. Clearly different or of a different kind."),
            new Definition(PartOfSpeech.ADVERB, "Uniquely. Written \"distinctly\"."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "An advanced search option."),
            new Definition(PartOfSpeech.NOUN, "Distinct is a parameter in this assignment.")),

    PLACEHOLDER(new Definition(PartOfSpeech.ADJECTIVE, "To be updated..."),
            new Definition(PartOfSpeech.ADJECTIVE, "To be updated..."),
            new Definition(PartOfSpeech.ADVERB, "To be updated..."),
            new Definition(PartOfSpeech.CONJUNCTION, "To be updated..."),
            new Definition(PartOfSpeech.INTERJECTION, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.PREPOSITION, "To be updated..."),
            new Definition(PartOfSpeech.PRONOUN, "To be updated..."),
            new Definition(PartOfSpeech.VERB, "To be updated...")),

    REVERSE(new Definition(PartOfSpeech.ADJECTIVE, "On back side."),
            new Definition(PartOfSpeech.ADJECTIVE, "Opposite to usual or previous arrangement."),
            new Definition(PartOfSpeech.NOUN, "A dictionary program's parameter."),
            new Definition(PartOfSpeech.NOUN, "Change to opposite direction."),
            new Definition(PartOfSpeech.NOUN, "The opposite."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.NOUN, "To be updated..."),
            new Definition(PartOfSpeech.VERB, "Change something to opposite."),
            new Definition(PartOfSpeech.VERB, "Go back"),
            new Definition(PartOfSpeech.VERB, "Revoke ruling."),
            new Definition(PartOfSpeech.VERB, "To be updated..."),
            new Definition(PartOfSpeech.VERB, "To be updated..."),
            new Definition(PartOfSpeech.VERB, "Turn something inside out.")),

    SIX(new Definition(PartOfSpeech.NOUN, "filler definition 40")),
    SEVEN(new Definition(PartOfSpeech.NOUN, "filler definition 41")),
    EIGHT(new Definition(PartOfSpeech.NOUN, "filler definition 42")),
    NINE(new Definition(PartOfSpeech.NOUN, "filler definition 43")),
    TEN(new Definition(PartOfSpeech.NOUN, "filler definition 44")),
    ELEVEN(new Definition(PartOfSpeech.NOUN, "filler definition 45")),
    TWELVE(new Definition(PartOfSpeech.NOUN, "filler definition 46")),
    THIRTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 47")),
    FOURTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 48")),
    FIFTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 49")),
    SIXTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 50")),
    SEVENTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 51")),
    EIGHTEEN(new Definition(PartOfSpeech.NOUN, "filler definition 52")),
    NINETEEN(new Definition(PartOfSpeech.NOUN, "filler definition 53"),
            new Definition(PartOfSpeech.NOUN, "filler definition 54"),
            new Definition(PartOfSpeech.NOUN, "filler definition 55"),
            new Definition(PartOfSpeech.NOUN, "filler definition 56"),
            new Definition(PartOfSpeech.NOUN, "filler definition 57"),
            new Definition(PartOfSpeech.NOUN, "filler definition 58"),
            new Definition(PartOfSpeech.NOUN, "filler definition 59"),
            new Definition(PartOfSpeech.NOUN, "filler definition 60"),
            new Definition(PartOfSpeech.NOUN, "filler definition 61"));

    private final Definition[] definitions;

    DictDatabase(Definition... definitions) {
        this.definitions = definitions;
    }

    /**
     * Load all the definitions stored in this enum database.
     *
     * @return A {@link HashMap} containing the {@link Definition}s of each entry.
     */
    public static HashMap<String, Definition[]> loadAll() {
        HashMap<String, Definition[]> entries = new HashMap<>();
        for (DictDatabase entry : DictDatabase.values()) {
            entries.put(entry.getTerm(), entry.getDefinitions());
        }
        return entries;
    }

    /**
     * @return The term of this entry in lowercase.
     * @apiNote The term is derived from the identifier of the enum declaration. Therefore,
     *         it is guaranteed to be unique.
     */
    public String getTerm() {
        return this.name().toLowerCase();
    }

    /**
     * @return The {@link Definition}s associated with this entry.
     */
    public Definition[] getDefinitions() {
        return this.definitions;
    }
}
