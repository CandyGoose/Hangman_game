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

    private static final int HEAD_INDEX = 0;
    private static final int BODY_INDEX = 1;
    private static final int LEFT_ARM_INDEX = 2;
    private static final int RIGHT_ARM_INDEX = 3;
    private static final int LEFT_LEG_INDEX = 4;
    private static final int RIGHT_LEG_INDEX = 5;

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
            hangman.append(BEAM).append(BODY_PARTS[HEAD_INDEX]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < ATTEMPTS_FOR_LEFT_ARM) {
            hangman.append(BEAM).append(BODY_PARTS[RIGHT_ARM_INDEX]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_BODY) {
            hangman.append(BEAM).append(BODY_PARTS[LEFT_ARM_INDEX]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_HEAD) {
            hangman.append(BEAM).append(BODY_PARTS[BODY_INDEX]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < ATTEMPTS_FOR_LEGS) {
            hangman.append(BEAM).append(BODY_PARTS[RIGHT_LEG_INDEX]).append("\n");
        } else if (attemptsLeft < ATTEMPTS_FOR_RIGHT_ARM) {
            hangman.append(BEAM).append(BODY_PARTS[LEFT_LEG_INDEX]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        hangman.append(BEAM).append("\n");
        hangman.append("===========");

        out.println(hangman);
    }
}
