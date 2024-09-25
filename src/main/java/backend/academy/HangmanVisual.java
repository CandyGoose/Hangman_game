package backend.academy;

public class HangmanVisual {

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

    public static void displayHangman(int attemptsLeft) {
        StringBuilder hangman = new StringBuilder(BASE_STRUCTURE);

        if (attemptsLeft < 6) {
            hangman.append(BEAM).append(BODY_PARTS[0]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < 3) {
            hangman.append(BEAM).append(BODY_PARTS[3]).append("\n");
        } else if (attemptsLeft < 4) {
            hangman.append(BEAM).append(BODY_PARTS[2]).append("\n");
        } else if (attemptsLeft < 5) {
            hangman.append(BEAM).append(BODY_PARTS[1]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        if (attemptsLeft < 1) {
            hangman.append(BEAM).append(BODY_PARTS[5]).append("\n");
        } else if (attemptsLeft < 2) {
            hangman.append(BEAM).append(BODY_PARTS[4]).append("\n");
        } else {
            hangman.append(BEAM).append("\n");
        }

        hangman.append(BEAM).append("\n");
        hangman.append("===========");

        System.out.println(hangman);
    }
}
