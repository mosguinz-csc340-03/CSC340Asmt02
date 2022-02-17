import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Dictionary {

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

    public Definition[] queryDict(String s) {
        final String[] args = s.split("\\s");
        final Queue<QueryOption> paramsToCheck = new LinkedList<>(List.of(QueryOption.values()));

        // CSO 25, abandon query
        if (args.length > 4) {
            // invalid
            return null;
        }

        if (args[0].equals("!help")) {
            // print help message
            return null;
        }

        Definition[] definitions = lookup(args[0]);

        if (definitions.length == 0) {
            // no entry
            return null;
        }

        Stream<Definition> stream = Arrays.stream(definitions).sorted();

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            boolean parsingFailed = true;

            for (QueryOption queryOption : paramsToCheck) {
                queryOption = queryOption.parse(arg);

                if (queryOption == null) {
                    continue;
                }

                parsingFailed = false;
                stream = switch (queryOption) {
                    case PART_OF_SPEECH -> {
                        PartOfSpeech pos = PartOfSpeech.parse(arg);
                        yield stream.filter(x -> x.partOfSpeech() == pos);
                    }
                    case DISTINCT -> stream.distinct();
                    case REVERSE -> stream.sorted(Collections.reverseOrder());
                };
            }

            if (parsingFailed) {
                // print parsing error message
            }

        }
        return null;
    }

}
