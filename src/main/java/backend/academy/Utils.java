package backend.academy;

import java.util.List;
import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static <T> T getRandomValue(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Список не должен быть пустым");
        }
        return values.get(random.nextInt(values.size()));
    }
}
