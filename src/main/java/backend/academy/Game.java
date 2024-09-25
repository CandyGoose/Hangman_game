package backend.academy;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private String word;
    private Set<Character> guessedLetters;
    private int attemptsLeft;
    private final int maxAttempts = 6;
    private PrintStream out;

    public Game(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Загаданное слово не может быть пустым.");
        }
        this.word = word.toLowerCase();
        this.guessedLetters = new HashSet<>();
        this.attemptsLeft = this.maxAttempts;
        this.out = System.out;
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
        if (!Character.isLetter(guess)) {
            throw new IllegalArgumentException("Буква должна быть символом.");
        }
        if (!guessedLetters.contains(guess)) {
            guessedLetters.add(guess);
            if (!word.contains(String.valueOf(guess))) {
                attemptsLeft--;
                out.println("Неправильная буква! Осталось попыток: " + attemptsLeft);
                HangmanVisual.displayHangman(attemptsLeft, out);
            } else {
                out.println("Правильная буква: " + guess);
            }
        } else {
            out.println("Эта буква уже была введена.");
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
        return attemptsLeft <= 0 || isWordGuessed();
    }

    public String getWord() {
        return word;
    }
}
