import java.util.HashMap;

public class Dictionary {

    private final HashMap<String, Definition[]> entries;

    public Dictionary() {
        this.entries = loadEntries();
    }

    private static HashMap<String, Definition[]> loadEntries() {
        HashMap<String, Definition[]> entries = new HashMap<>();
        for (DictEntry entry : DictEntry.values()) {
            entries.put(entry.getTerm(), entry.getDefinitions());
        }
        return entries;
    }

}
