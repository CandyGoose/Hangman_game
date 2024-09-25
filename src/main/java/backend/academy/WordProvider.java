package backend.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordProvider {
    private static final Map<String, Map<String, List<String>>> WORD_DICTIONARY;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = WordProvider.class.getClassLoader().getResourceAsStream("words.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл словаря words.json не найден в ресурсах.");
            }
            WORD_DICTIONARY = mapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения словаря: " + e.getMessage(), e);
        }
    }

    public static List<String> getCategories() {
        return List.copyOf(WORD_DICTIONARY.keySet());
    }

    public static List<String> getDifficultyLevels() {
        return List.of("Легкий", "Средний", "Тяжелый");
    }

    public static String getRandomWord(String category, String difficulty) {
        Map<String, List<String>> categoryWords = WORD_DICTIONARY.get(category);
        if (categoryWords == null) {
            throw new RuntimeException("Категория '" + category + "' не найдена в словаре.");
        }

        List<String> words = categoryWords.get(difficulty);
        if (words == null || words.isEmpty()) {
            throw new RuntimeException("Нет доступных слов для сложности '" + difficulty + "' в категории '" + category + "'.");
        }

        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
