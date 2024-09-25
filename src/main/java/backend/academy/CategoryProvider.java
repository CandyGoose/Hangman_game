package backend.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CategoryProvider {
    private static final Logger logger = LogManager.getLogger(CategoryProvider.class);
    private static final Map<String, Map<String, List<String>>> WORD_DICTIONARY;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = CategoryProvider.class.getClassLoader().getResourceAsStream("words.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл словаря words.json не найден в ресурсах.");
            }
            WORD_DICTIONARY = mapper.readValue(inputStream, Map.class);
            logger.info("Словарь загружен успешно");
        } catch (IOException e) {
            logger.error("Ошибка чтения словаря: ", e);
            throw new RuntimeException("Ошибка чтения словаря: " + e.getMessage(), e);
        }
    }

    public static List<String> getCategories() {
        try {
            return List.copyOf(WORD_DICTIONARY.keySet());
        } catch (Exception e) {
            logger.error("Ошибка получения категорий: ", e);
            throw new RuntimeException("Ошибка получения категорий: " + e.getMessage(), e);
        }
    }

    public static List<String> getDifficultyLevels() {
        return List.of("Легкий", "Средний", "Тяжелый");
    }

    public static String getRandomWord(String category, String difficulty) {
        try {
            Map<String, List<String>> categoryWords = WORD_DICTIONARY.get(category);
            if (categoryWords == null) {
                throw new RuntimeException("Категория '" + category + "' не найдена в словаре.");
            }

            List<String> words = categoryWords.get(difficulty);
            if (words == null || words.isEmpty()) {
                throw new RuntimeException("Нет доступных слов для сложности '" + difficulty + "' в категории '" + category + "'.");
            }

            Random random = new Random();
            String word = words.get(random.nextInt(words.size()));
            logger.info("Выбрано слово: {}", word);
            return word;
        } catch (Exception e) {
            logger.error("Ошибка получения случайного слова: ", e);
            throw new RuntimeException("Ошибка получения случайного слова: " + e.getMessage(), e);
        }
    }
}
