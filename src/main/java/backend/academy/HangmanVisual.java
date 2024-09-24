package backend.academy;

public class HangmanVisual {
    private static final String[] HANGMAN_STATES = {
        """
           -----
           |   |
               |
               |
               |
               |
        =========""",
        """
           -----
           |   |
           O   |
               |
               |
               |
        =========""",
        """
           -----
           |   |
           O   |
           |   |
               |
               |
        =========""",
        """
           -----
           |   |
           O   |
          /|   |
               |
               |
        =========""",
        """
           -----
           |   |
           O   |
          /|\\  |
               |
               |
        =========""",
        """
           -----
           |   |
           O   |
          /|\\  |
          /    |
               |
        =========""",
        """
           -----
           |   |
           O   |
          /|\\  |
          / \\  |
               |
        ========="""
    };

    public static void displayHangman(int attemptsLeft) {
        int stateIndex = HANGMAN_STATES.length - attemptsLeft - 1;
        if (stateIndex >= 0 && stateIndex < HANGMAN_STATES.length) {
            System.out.println(HANGMAN_STATES[stateIndex]);
        }
    }
}
