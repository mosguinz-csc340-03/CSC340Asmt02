import java.util.HashMap;

public class Dictionary {

    private final HashMap<String, Definition[]> entries;

    public Dictionary() {
        this.entries = loadEntries();
    }

    /**
     * Load all the definitions stored in the enum database.
     *
     * @return A {@link HashMap} containing the {@link Definition}s of each entry.
     */
    private static HashMap<String, Definition[]> loadEntries() {
        HashMap<String, Definition[]> entries = new HashMap<>();
        int entryCount = 0, defCount = 0;

        for (DictEntry entry : DictEntry.values()) {
            String term = entry.getTerm();
            Definition[] definitions = entry.getDefinitions();
            
            entries.put(term, definitions);
            entryCount++;
            defCount += definitions.length;
        }

        return entries;
    }

}
