package backend.academy;

import java.util.List;
import java.util.Random;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final Random RANDOM = new Random();

    public static <T> T getRandomValue(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Список не должен быть пустым");
        }
        return values.get(RANDOM.nextInt(values.size()));
    }
}
