package backend.academy;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private String word;
    private Set<Character> guessedLetters;
    private int attemptsLeft;
    private final int MAX_ATTEMPTS = 6;

    public Game(String word) {
        this.word = word.toLowerCase();
        this.guessedLetters = new HashSet<>();
        this.attemptsLeft = MAX_ATTEMPTS;
    }

    public String getMaskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                maskedWord.append(letter);
            } else {
                maskedWord.append("_");
            }
        }
        return maskedWord.toString();
    }

    public void makeGuess(char guess) {
        if (!guessedLetters.contains(guess)) {
            guessedLetters.add(guess);
            if (!word.contains(String.valueOf(guess))) {
                attemptsLeft--;
                System.out.println("Неправильная буква! Осталось попыток: " + attemptsLeft);
                HangmanVisual.displayHangman(attemptsLeft);
            }
        }
    }

    public boolean isWordGuessed() {
        for (char letter : word.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    public String getWord() {
        return word;
    }
}
