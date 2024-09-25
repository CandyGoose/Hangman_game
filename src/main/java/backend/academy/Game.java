package backend.academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private static final Logger logger = LogManager.getLogger(Game.class);
    private String word;
    private Set<Character> guessedLetters;
    private int attemptsLeft;
    private final int maxAttempts = 6;

    public Game(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Загаданное слово не может быть пустым.");
        }
        this.word = word.toLowerCase();
        this.guessedLetters = new HashSet<>();
        this.attemptsLeft = this.maxAttempts;
        logger.info("Игра началась с загаданным словом: {}", word);
    }

    public String getMaskedWord() {
        try {
            StringBuilder maskedWord = new StringBuilder();
            for (char letter : word.toCharArray()) {
                if (guessedLetters.contains(letter)) {
                    maskedWord.append(letter);
                } else {
                    maskedWord.append("_");
                }
            }
            return maskedWord.toString();
        } catch (Exception e) {
            logger.error("Ошибка при генерации замаскированного слова: ", e);
            throw new RuntimeException("Ошибка при генерации замаскированного слова: " + e.getMessage(), e);
        }
    }

    public void makeGuess(char guess) {
        if (!Character.isLetter(guess)) {
            throw new IllegalArgumentException("Буква должна быть символом.");
        }
        if (!guessedLetters.contains(guess)) {
            guessedLetters.add(guess);
            if (!word.contains(String.valueOf(guess))) {
                attemptsLeft--;
                System.out.println("Неправильная буква! Осталось попыток: " + attemptsLeft);
                HangmanVisual.displayHangman(attemptsLeft);
            }
        } else {
            System.out.println("Эта буква уже была введена.");
        }
    }

    public boolean isWordGuessed() {
        try {
            for (char letter : word.toCharArray()) {
                if (!guessedLetters.contains(letter)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Ошибка при проверке состояния слова: ", e);
            throw new RuntimeException("Ошибка при проверке состояния слова: " + e.getMessage(), e);
        }
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0 || isWordGuessed();
    }

    public String getWord() {
        return word;
    }
}
