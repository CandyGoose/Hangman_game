package backend.academy;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CategoryProvider {
    private static final Map<String, Map<String, List<String>>> WORD_CATEGORIES = Map.of(
        "Животные", Map.of(
            "Легкий", List.of("кот", "пёс", "заяц"),
            "Средний", List.of("тигр", "волк", "жираф"),
            "Тяжелый", List.of("бегемот", "аллигатор", "арабский верблюд")
        ),
        "Техника", Map.of(
            "Легкий", List.of("робот", "дрон", "лампа"),
            "Средний", List.of("компьютер", "телефон", "телевизор"),
            "Тяжелый", List.of("микроволновка", "автомобиль", "стиральная машина")
        )
    );

    public static List<String> getCategories() {
        return List.copyOf(WORD_CATEGORIES.keySet());
    }

    public static List<String> getDifficultyLevels() {
        return List.of("Легкий", "Средний", "Тяжелый");
    }

    public static String getRandomWord(String category, String difficulty) {
        List<String> words = WORD_CATEGORIES.get(category).get(difficulty);
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}

