import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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

    public static void printHelp() {
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


    /**
     * Print a message describing why the provided {@code arg} failed to parse.
     *
     * @param argIndex The index of the argument that failed to parse, where the zeroth index is the
     *                 search term.
     * @param arg      The argument itself.
     * @apiNote The method does not perform any parsing or validation of the provided {@code
     *         arg}. Instead, it infers the missing arguments from the provided {@code argIndex} and
     *         the fixed ordering of the parameters (provided by {@link QueryOption#values()}).
     */
    public static void printParsingError(int argIndex, String arg) {

        List<QueryOption> queryOptions = List.of(QueryOption.values())
                .subList(argIndex - 1, QueryOption.values().length);

        String argOrdinal = switch (argIndex) {
            case 0 -> "1st";
            case 1 -> "2nd";
            case 2 -> "3rd";
            default -> String.format("%dth", argIndex + 1);
        };

        Stream<String> msgStream = queryOptions.stream().map(option ->
                String.format(
                        "<The entered %s parameter '%s' is NOT %s.>",
                        argOrdinal, arg, option
                )
        );
        String argDisregard = String.format(
                "<The entered %s parameter '%s' was disregarded.>",
                argOrdinal, arg
        );
        String argHint = String.format(
                "<The %s parameter should be %s.>",
                argOrdinal, String.join(" or ", queryOptions.stream()
                        .map(QueryOption::toString)
                        .toArray(String[]::new))
        );

        ArrayList<String> messages = new ArrayList<>(msgStream.toList());
        messages.add(argDisregard);
        messages.add(argHint);

        printToConsole(messages.toArray(String[]::new));
    }


    /**
     * Load all dictionary entries and begin the session.
     */
    public void startSession() {
        System.out.println("! Loading data...");
        Dictionary dict = new Dictionary();
        System.out.printf("! Loading completed...%n%n"
                        + "===== DICTIONARY 340 JAVA =====%n"
                        + "----- Keywords: %d%n"
                        + "----- Definitions: %d%n%n",
                dict.getEntryCount(),
                dict.getDefinitionCount());

        while (true) {

            final String input = promptInput();

            if (input.equalsIgnoreCase("!q")) {
                System.out.println("\n-----THANK YOU-----");
                return;
            }

            // CSO 2
            if (input.isEmpty() || input.isBlank()) {
                printHelp();
                continue;
            }

            final String[] args = input.split("\\s+");
            final String searchTerm = args[0];

            // CSO 1, 25, and 28
            if (args.length > 4 || searchTerm.equals("!help")) {
                printHelp();
                continue;
            }

            Definition[] res = dict.queryDict(args);
            printResults(searchTerm, res);

        }
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
