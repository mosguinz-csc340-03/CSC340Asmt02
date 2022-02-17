import java.util.Scanner;

public class DictClient {

    private static final Scanner scanner = new Scanner(System.in);
    private int queryCount = 0;

    private static void printToConsole(String... messages) {
        System.out.println("|".indent(4));
        for (String message : messages) {
            System.out.println(message.indent(4));
        }
        System.out.println("|".indent(4));
    }

    public void startSession() {
        Dictionary dict = new Dictionary();
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
