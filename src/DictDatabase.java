/**
 * A "database" containing all the definitions.
 */
public enum DictDatabase {

    arrow(new Definition(PartOfSpeech.NOUN, "Here is one arrow: <IMG> -=>> </IMG>")),

    book(new Definition(PartOfSpeech.NOUN, "A set of pages."),
            new Definition(PartOfSpeech.NOUN,
                    "A written work published in printed or electronic form."),
            new Definition(PartOfSpeech.VERB, "To arrange for someone to have a seat on a plane."),
            new Definition(PartOfSpeech.VERB, "To arrange something on a particular date.")),

    distinct(new Definition(PartOfSpeech.ADJECTIVE, "Familiar. Worked in Java."),
            new Definition(PartOfSpeech.ADJECTIVE,
                    "Unique. No duplicates. Clearly different or of a different kind."),
            new Definition(PartOfSpeech.ADVERB, "Uniquely. Written \"distinctly\"."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "A keyword in this assignment."),
            new Definition(PartOfSpeech.NOUN, "An advanced search option."),
            new Definition(PartOfSpeech.NOUN, "Distinct is a parameter in this assignment.")),

    placeholder(new Definition(PartOfSpeech.ADJECTIVE, "To be updated..."),
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

    reverse(new Definition(PartOfSpeech.ADJECTIVE, "On back side."),
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

    six(new Definition(PartOfSpeech.NOUN, "filler definition 40")),
    seven(new Definition(PartOfSpeech.NOUN, "filler definition 41")),
    eight(new Definition(PartOfSpeech.NOUN, "filler definition 42")),
    nine(new Definition(PartOfSpeech.NOUN, "filler definition 43")),
    ten(new Definition(PartOfSpeech.NOUN, "filler definition 44")),
    eleven(new Definition(PartOfSpeech.NOUN, "filler definition 45")),
    twelve(new Definition(PartOfSpeech.NOUN, "filler definition 46")),
    thirteen(new Definition(PartOfSpeech.NOUN, "filler definition 47")),
    fourteen(new Definition(PartOfSpeech.NOUN, "filler definition 48")),
    fifteen(new Definition(PartOfSpeech.NOUN, "filler definition 49")),
    sixteen(new Definition(PartOfSpeech.NOUN, "filler definition 50")),
    seventeen(new Definition(PartOfSpeech.NOUN, "filler definition 51")),
    eighteen(new Definition(PartOfSpeech.NOUN, "filler definition 52")),
    nineteen(new Definition(PartOfSpeech.NOUN, "filler definition 53"),
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

    public String getTerm() {
        return this.name().toLowerCase();
    }

    public Definition[] getDefinitions() {
        return this.definitions;
    }
}
