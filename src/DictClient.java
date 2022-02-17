import java.util.Scanner;

public class DictClient {

    private static final Scanner scanner = new Scanner(System.in);
    private int queryCount = 0;

    public static void main(String[] args) {
        new DictClient().startSession();
    }

    private static void printToConsole(String... messages) {
        System.out.println("|".indent(4));
        for (String message : messages) {
            System.out.println(message.indent(4));
        }
        System.out.println("|".indent(4));
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
