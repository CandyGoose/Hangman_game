package backend.academy;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CategoryProvider {
    private static final Map<String, List<String>> CATEGORIES = Map.of(
        "Легкий", List.of("кот", "дом", "мир"),
        "Средний", List.of("яблоко", "машина", "программа"),
        "Тяжелый", List.of("энциклопедия", "программирование", "автономность")
    );

    public static String getRandomWord(String difficulty) {
        Random random = new Random();
        List<String> words = CATEGORIES.get(difficulty);
        return words.get(random.nextInt(words.size()));
    }

    public static List<String> getDifficultyLevels() {
        return List.copyOf(CATEGORIES.keySet());
    }
}

