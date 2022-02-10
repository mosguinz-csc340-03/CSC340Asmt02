import java.util.Map;

enum PartOfSpeech {
    ADJECTIVE, ADVERB, CONJUNCTION, INTERJECTION, NOUN, PREPOSITION, VERB
}

/**
 * Represents a definition for a word. A word may contain multiple definitions.
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
        int posDelta = this.partOfSpeech.compareTo(o.partOfSpeech);
        if (posDelta == 0) {
            return this.definition.compareTo(o.definition);
        }
        return posDelta;
    }
}