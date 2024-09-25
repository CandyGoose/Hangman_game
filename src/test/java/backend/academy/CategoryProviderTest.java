package backend.academy;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryProviderTest {

    @Test
    void testGetRandomWord() {
        String word = CategoryProvider.getRandomWord("Животные", "Легкий");
        assertNotNull(word);
        assertTrue(List.of("кот", "пёс", "заяц").contains(word));
    }

    @Test
    void testGetCategories() {
        List<String> categories = CategoryProvider.getCategories();
        assertEquals(3, categories.size());
        assertTrue(categories.contains("Животные"));
        assertTrue(categories.contains("Техника"));
        assertTrue(categories.contains("Фрукты"));
    }

    @Test
    void testGetDifficultyLevels() {
        List<String> levels = CategoryProvider.getDifficultyLevels();
        assertEquals(3, levels.size());
        assertTrue(levels.contains("Легкий"));
        assertTrue(levels.contains("Средний"));
        assertTrue(levels.contains("Тяжелый"));
    }
}
