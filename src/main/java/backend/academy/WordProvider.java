package backend.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordProvider {

    private static final Logger LOGGER = LogManager.getLogger(WordProvider.class);
    private static final String ERROR_READING_DICTIONARY = "Ошибка чтения словаря: ";
    private static final String ERROR_GETTING_CATEGORIES = "Ошибка получения категорий: ";
    private static final String ERROR_GETTING_RANDOM_WORD = "Ошибка получения случайного слова: ";
    private static final String WORD_KEY = "слово";
    private static final String HINT_KEY = "подсказка";
    private static final Map<String, Map<String, List<Map<String, String>>>> WORD_DICTIONARY;

    private WordProvider() {
        throw new UnsupportedOperationException("Utility class");
    }

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream =
                 WordProvider.class.getClassLoader().getResourceAsStream("words.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл словаря words.json не найден в ресурсах.");
            }
            WORD_DICTIONARY = mapper.readValue(inputStream, Map.class);
            LOGGER.info("Словарь загружен успешно");
        } catch (IOException e) {
            LOGGER.error(ERROR_READING_DICTIONARY, e);
            throw new RuntimeException(ERROR_READING_DICTIONARY + e.getMessage(), e);
        }
    }

    public static List<String> getCategories() {
        try {
            return List.copyOf(WORD_DICTIONARY.keySet());
        } catch (Exception e) {
            LOGGER.error(ERROR_GETTING_CATEGORIES, e);
            throw new RuntimeException(ERROR_GETTING_CATEGORIES + e.getMessage(), e);
        }
    }

    public static List<String> getDifficultyLevels() {
        return List.of("Легкий", "Средний", "Тяжелый");
    }

    public static WordWithHint getRandomWord(String category, String difficulty) {
        try {
            Map<String, List<Map<String, String>>> categoryWords = WORD_DICTIONARY.get(category);
            if (categoryWords == null) {
                throw new RuntimeException("Категория '" + category + "' не найдена в словаре.");
            }

            List<Map<String, String>> words = categoryWords.get(difficulty);
            if (words == null || words.isEmpty()) {
                throw new RuntimeException(
                    "Нет доступных слов для сложности '" + difficulty + "' в категории '" + category + "'."
                );
            }

            Random random = new Random();
            Map<String, String> wordEntry = words.get(random.nextInt(words.size()));
            String word = wordEntry.get(WORD_KEY);
            String hint = wordEntry.get(HINT_KEY);
            LOGGER.info("Слово выбрано");
            return new WordWithHint(word, hint);
        } catch (Exception e) {
            LOGGER.error(ERROR_GETTING_RANDOM_WORD, e);
            throw new RuntimeException(ERROR_GETTING_RANDOM_WORD + e.getMessage(), e);
        }
    }
}
