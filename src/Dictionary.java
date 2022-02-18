import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Dictionary {

    private static final Definition[] NO_DEFINITIONS = {};

    private final HashMap<String, Definition[]> entries;
    private final int entryCount, definitionCount;

    public Dictionary() {
        HashMap<String, Definition[]> entries = new HashMap<>();
        int entryCount = 0, definitionCount = 0;

        for (DictEntry entry : DictEntry.values()) {
            String term = entry.getTerm();
            Definition[] definitions = entry.getDefinitions();

            entries.put(term, definitions);
            entryCount++;
            definitionCount += definitions.length;
        }

        this.entries = entries;
        this.entryCount = entryCount;
        this.definitionCount = definitionCount;
    }

    /**
     * @return The number of entries in this dictionary. An entry is a unique keyword stored in the
     *         dictionary.
     */
    public int getEntryCount() {
        return entryCount;
    }

    /**
     * @return The number of definitions in this dictionary. An entry may contain multiple
     *         definitions, where each definition need not be unique.
     */
    public int getDefinitionCount() {
        return definitionCount;
    }

    private Definition[] lookup(String s) {
        return entries.get(s.toLowerCase());
    }

    /**
     * Query the dictionary with the provided arguments.
     *
     * @param args The arguments for the query, where the first item (zeroth index) is the search
     *             term.
     * @return The query result. Returns an empty array if no matches are found.
     */
    public Definition[] queryDict(String[] args) {
        final String searchTerm = args[0];
        Definition[] definitions = lookup(searchTerm);

        if (definitions == null) {
            return NO_DEFINITIONS;
        }

        Queue<QueryOption> paramsToCheck = new LinkedList<>(List.of(QueryOption.values()));
        Stream<Definition> stream = Arrays.stream(definitions).sorted();

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            boolean parsingFailed = true;

            while (paramsToCheck.size() > 0) {

                QueryOption thisParam = paramsToCheck.poll().parse(arg);

                if (thisParam == null) {
                    continue;
                }

                parsingFailed = false;
                stream = switch (thisParam) {
                    case PART_OF_SPEECH -> {
                        PartOfSpeech pos = PartOfSpeech.parse(arg);
                        yield stream.filter(x -> x.partOfSpeech() == pos);
                    }
                    case DISTINCT -> stream.distinct();
                    case REVERSE -> stream.sorted(Collections.reverseOrder());
                };
                break;

            }

            if (parsingFailed) {
                DictClient.printParsingError(i, arg);
            }
        }

        return stream.toArray(Definition[]::new);
    }

}
