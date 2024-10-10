package backend.academy;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;

        try {
            String selectedCategory = chooseCategory(out, scanner);
            String selectedDifficulty = chooseDifficulty(out, scanner);

            WordWithHint word = WordProvider.getRandomWord(selectedCategory, selectedDifficulty);
            Game game = new Game(word.getWord(), word.getHint());

            out.println("Категория: " + selectedCategory);
            out.println("Сложность: " + selectedDifficulty);

            playGame(out, scanner, game);

        } catch (Exception e) {
            LOGGER.error("Произошла ошибка: ", e);
        } finally {
            scanner.close();
        }
    }

    private static String chooseCategory(PrintStream out, Scanner scanner) {
        List<String> categories = WordProvider.getCategories();
        out.println("Выберите категорию (или нажмите Enter для случайного выбора): ");
        for (int i = 0; i < categories.size(); i++) {
            out.println((i + 1) + ". " + categories.get(i));
        }

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            String selectedCategory = Utils.getRandomValue(categories);
            out.println("Случайная категория: " + selectedCategory);
            return selectedCategory;
        } else {
            try {
                int categoryChoice = Integer.parseInt(input);
                if (categoryChoice < 1 || categoryChoice > categories.size()) {
                    String selectedCategory = Utils.getRandomValue(categories);
                    out.println("Неправильный выбор. Используется случайная категория: " + selectedCategory);
                    return selectedCategory;
                } else {
                    return categories.get(categoryChoice - 1);
                }
            } catch (NumberFormatException e) {
                String selectedCategory = Utils.getRandomValue(categories);
                out.println("Некорректный ввод. Используется случайная категория: " + selectedCategory);
                return selectedCategory;
            }
        }
    }

    private static String chooseDifficulty(PrintStream out, Scanner scanner) {
        List<String> difficultyLevels = WordProvider.getDifficultyLevels();
        out.println("Выберите уровень сложности (или нажмите Enter для случайного выбора): ");
        for (int i = 0; i < difficultyLevels.size(); i++) {
            out.println((i + 1) + ". " + difficultyLevels.get(i));
        }

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            String selectedDifficulty = Utils.getRandomValue(difficultyLevels);
            out.println("Случайная сложность: " + selectedDifficulty);
            return selectedDifficulty;
        } else {
            try {
                int difficultyChoice = Integer.parseInt(input);
                if (difficultyChoice < 1 || difficultyChoice > difficultyLevels.size()) {
                    String selectedDifficulty = Utils.getRandomValue(difficultyLevels);
                    out.println("Неправильный выбор. Используется случайная сложность: " + selectedDifficulty);
                    return selectedDifficulty;
                } else {
                    return difficultyLevels.get(difficultyChoice - 1);
                }
            } catch (NumberFormatException e) {
                String selectedDifficulty = Utils.getRandomValue(difficultyLevels);
                LOGGER.error("Некорректный ввод. Выбран случайный уровень сложности: {}", selectedDifficulty, e);
                out.println("Некорректный ввод. Используется случайная сложность: " + selectedDifficulty);
                return selectedDifficulty;
            }
        }
    }

    private static void playGame(PrintStream out, Scanner scanner, Game game) {
        while (!game.isGameOver()) {
            out.println(game.getMaskedWord());
            out.println("Введите букву или напишите 'подсказка': ");
            String letterInput = scanner.nextLine().toLowerCase();

            if (letterInput.equals("подсказка")) {
                out.println("Подсказка: " + game.getHint());
            } else if (letterInput.length() != 1 || !Character.isLetter(letterInput.charAt(0))) {
                out.println("Ошибка ввода. Введите одну букву.");
                continue;
            } else {
                char guess = letterInput.charAt(0);
                game.makeGuess(guess);
            }

            if (game.isWordGuessed()) {
                out.println("Поздравляем! Вы угадали слово: " + game.getWord());
                break;
            } else if (game.isGameOver()) {
                out.println("Вы проиграли. Загаданное слово было: " + game.getWord());
            }
        }
    }
}
