package backend.academy;

import java.util.List;
import java.util.Random;

public class WordProvider {
    private static final List<String> WORDS = List.of("яблоко", "машина", "компьютер", "программа", "собака");

    public static String getRandomWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}
