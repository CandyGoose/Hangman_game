package backend.academy;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordProviderTest {

    @Test
    void testGetRandomWord() {
        WordWithHint wordWithHint = WordProvider.getRandomWord("Животные", "Легкий");
        assertNotNull(wordWithHint);
        assertTrue(List.of("кот", "пёс", "заяц", "муха", "птица").contains(wordWithHint.getWord()));
    }

    @Test
    void testGetCategories() {
        List<String> categories = WordProvider.getCategories();
        assertEquals(3, categories.size());
        assertTrue(categories.contains("Животные"));
        assertTrue(categories.contains("Техника"));
        assertTrue(categories.contains("Фрукты"));
    }

    @Test
    void testGetDifficultyLevels() {
        List<String> levels = WordProvider.getDifficultyLevels();
        assertEquals(3, levels.size());
        assertTrue(levels.contains("Легкий"));
        assertTrue(levels.contains("Средний"));
        assertTrue(levels.contains("Тяжелый"));
    }
}
