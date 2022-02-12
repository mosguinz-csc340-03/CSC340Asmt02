import java.util.HashMap;
import java.util.Scanner;

enum SearchOption {
    DISTINCT, REVERSE
}

public class Dictionary {

    private static final Scanner scanner = new Scanner(System.in);
    private final HashMap<String, Definition[]> entries;
    private int queryCount = 0;

    private Dictionary() {
        this.entries = loadEntries();
    }

    public static void main(String[] args) {
        Dictionary.startSession();
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

        System.out.printf("! Loading data...%n"
                        + "! Loading completed...%n"
                        + "===== DICTIONARY 340 JAVA =====%n"
                        + "----- Keywords: %d%n"
                        + "----- Definitions: %d%n",
                entryCount, defCount);

        return entries;
    }

    public static void startSession() {
        Dictionary dict = new Dictionary();
        String input;
        do {
            input = dict.promptInput();
        } while (!input.equals("!q"));
    }

    /**
     * Display prompt for user input.
     *
     * @return The input with all leading and trailing spaces trimmed.
     */
    private String promptInput() {
        System.out.printf("Search [%d]: ", ++queryCount);
        return scanner.nextLine().trim();
    }

}
