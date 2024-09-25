package backend.academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    @Test
    void testCorrectGuess() {
        Game game = new Game("яблоко", "");
        game.makeGuess('я');
        assertEquals("я_____", game.getMaskedWord());
    }

    @Test
    void testIncorrectGuess() {
        Game game = new Game("яблоко", "");
        game.makeGuess('с');
        assertEquals("______", game.getMaskedWord());
    }

    @Test
    void testWinCondition() {
        Game game = new Game("кот", "");
        game.makeGuess('к');
        game.makeGuess('о');
        game.makeGuess('т');
        assertTrue(game.isWordGuessed());
    }

    @Test
    void testGameOverCondition() {
        Game game = new Game("кот", "");
        game.makeGuess('с');
        game.makeGuess('а');
        game.makeGuess('н');
        game.makeGuess('р');
        game.makeGuess('п');
        game.makeGuess('д');
        assertTrue(game.isGameOver());
    }
    
    @Test
    void testGetHintInGame() {
        Game game = new Game("кот", "Домашнее животное");
        assertEquals("Домашнее животное", game.getHint());
    }

    @Test
    void testInvalidWordThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game("", "Подсказка");
        });
        assertEquals("Загаданное слово не может быть пустым.", exception.getMessage());
    }

    @Test
    void testInvalidGuessThrowsException() {
        Game game = new Game("кот", "Домашнее животное");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.makeGuess('1');
        });
        assertEquals("Буква должна быть символом.", exception.getMessage());
    }
}
