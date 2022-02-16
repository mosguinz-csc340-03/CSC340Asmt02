import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

abstract class QueryParam {

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

    private Definition[] getEntries(String s) {
        return entries.get(s.toLowerCase());
    }

    private DictQuery queryDict(String s) {
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

        Definition[] definitions = getEntries(args[0]);

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
