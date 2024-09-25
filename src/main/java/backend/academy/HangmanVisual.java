package backend.academy;

import java.io.PrintStream;

public class HangmanVisual {

    private HangmanVisual() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final int MAX_ATTEMPTS = 6;
    private static final int ATTEMPTS_FOR_HEAD = 5;
    private static final int ATTEMPTS_FOR_BODY = 4;
    private static final int ATTEMPTS_FOR_LEFT_ARM = 3;
    private static final int ATTEMPTS_FOR_RIGHT_ARM = 2;
    private static final int ATTEMPTS_FOR_LEGS = 1;

    private static final String BEAM = "|      ";
    private static final String BASE_STRUCTURE = """
        ---------
        """ + BEAM + """
         |
        """;

    private static final String[] BODY_PARTS = {
        " O  ",
        " |  ",
        "/|  ",
        "/|\\",
        "/   ",
        "/ \\"
    };

    public static void displayHangman(int attemptsLeft, PrintStream out) {
        StringBuilder hangman = new StringBuilder(BASE_STRUCTURE);

        if (attemptsLeft < MAX_ATTEMPTS) {
            hangman.append(BEAM).append(BODY_PARTS[0]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < ATTEMPTS_FOR_LEFT_ARM) {
            hangman.append(BEAM).append(BODY_PARTS[3]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_BODY) {
            hangman.append(BEAM).append(BODY_PARTS[2]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_HEAD) {
            hangman.append(BEAM).append(BODY_PARTS[1]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < ATTEMPTS_FOR_LEGS) {
            hangman.append(BEAM).append(BODY_PARTS[5]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_RIGHT_ARM) {
            hangman.append(BEAM).append(BODY_PARTS[4]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        hangman.append(BEAM).append("\n");
        hangman.append("===========");

        out.println(hangman);
    }
}
