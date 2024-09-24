package backend.academy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CategoryProviderTest {

    @Test
    void testGetRandomWord() {
        String word = CategoryProvider.getRandomWord("Легкий");
        assertNotNull(word);
        assertTrue(List.of("кот", "дом", "мир").contains(word));
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
