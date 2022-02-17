import java.util.Arrays;
import java.util.Scanner;

public class DictClient {

    private static final Scanner scanner = new Scanner(System.in);
    private int queryCount = 0;

    public static void main(String[] args) {
        new DictClient().startSession();
    }

    private static void printToConsole(String... messages) {
        System.out.print("|".indent(4));
        for (String message : messages) {
            System.out.print(message.indent(4));
        }
        System.out.print("|".indent(4));
    }

    private static void printHelp() {
        printToConsole(
                "PARAMETER HOW-TO,  please enter:",
                "1. A search key -then 2. An optional part of speech -then",
                "3. An optional 'distinct' -then 4. An optional 'reverse'"
        );
    }

    /**
     * Print search results. If there are no definitions to display, prints a "not found" message.
     *
     * @param term        The search term.
     * @param definitions An array of {@link Definition}s from the query.
     */
    private static void printResults(String term, Definition[] definitions) {
        if (definitions.length == 0) {
            printToConsole("<NOT FOUND> To be considered for the next release. Thank you.");
            printHelp();
            return;
        }

        StringBuilder sb = new StringBuilder(term.toLowerCase());
        sb.setCharAt(0, Character.toUpperCase(term.charAt(0)));
        final String formattedTerm = sb.toString();

        printToConsole(
                Arrays.stream(definitions).map(def -> String.format("%s [%s] : %s",
                        formattedTerm, def.partOfSpeech(), def.definition()
                )).toArray(String[]::new)
        );
    }

    public void startSession() {
        System.out.println("! Loading data...");
        Dictionary dict = new Dictionary();
        System.out.printf("! Loading completed...%n%n"
                        + "===== DICTIONARY 340 JAVA =====%n"
                        + "----- Keywords: %d%n"
                        + "----- Definitions: %d%n%n",
                dict.getEntryCount(),
                dict.getDefinitionCount());

        String input;
        do {
            input = promptInput();
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
