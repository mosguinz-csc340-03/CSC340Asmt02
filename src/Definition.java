import java.util.Map;

enum PartOfSpeech {
    ADJECTIVE, ADVERB, CONJUNCTION, INTERJECTION, NOUN, PREPOSITION, PRONOUN, VERB
}

/**
 * Represents a definition entry for a word. A word may contain multiple definitions.
 *
 * @param partOfSpeech The part of speech that the definition applies to.
 * @param definition   The definition.
 */
public record Definition(PartOfSpeech partOfSpeech, String definition)
        implements Map.Entry<PartOfSpeech, String>, Comparable<Definition> {

    @Override
    public PartOfSpeech getKey() {
        return this.partOfSpeech;
    }

    @Override
    public String getValue() {
        return this.definition;
    }

    @Override
    public String setValue(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", partOfSpeech, definition);
    }

    @Override
    public int compareTo(Definition o) {
        int posDelta = this.partOfSpeech.name().compareTo(o.partOfSpeech.name());
        if (posDelta == 0) {
            return this.definition.compareTo(o.definition);
        }
        return posDelta;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            return compareTo((Definition) o) == 0;
        }
        return false;
    }
}
