package backend.academy;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        PrintStream out = System.out;


        try {
            List<String> categories = CategoryProvider.getCategories();
            out.println("Выберите категорию (или нажмите Enter для случайного выбора): ");
            for (int i = 0; i < categories.size(); i++) {
                out.println((i + 1) + ". " + categories.get(i));
            }

            String selectedCategory;
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                selectedCategory = categories.get(random.nextInt(categories.size()));
                out.println("Случайная категория: " + selectedCategory);
            } else {
                try {
                    int categoryChoice = Integer.parseInt(input);
                    if (categoryChoice < 1 || categoryChoice > categories.size()) {
                        selectedCategory = categories.get(random.nextInt(categories.size()));
                        out.println("Неправильный выбор. Используется случайная категория: " + selectedCategory);
                    } else {
                        selectedCategory = categories.get(categoryChoice - 1);
                    }
                } catch (NumberFormatException e) {
                    selectedCategory = categories.get(random.nextInt(categories.size()));
                    out.println("Некорректный ввод. Используется случайная категория: " + selectedCategory);
                }
            }

            List<String> difficultyLevels = CategoryProvider.getDifficultyLevels();
            out.println("Выберите уровень сложности (или нажмите Enter для случайного выбора): ");
            for (int i = 0; i < difficultyLevels.size(); i++) {
                out.println((i + 1) + ". " + difficultyLevels.get(i));
            }

            String selectedDifficulty;
            input = scanner.nextLine();

            if (input.isEmpty()) {
                selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                out.println("Случайная сложность: " + selectedDifficulty);
            } else {
                try {
                    int difficultyChoice = Integer.parseInt(input);
                    if (difficultyChoice < 1 || difficultyChoice > difficultyLevels.size()) {
                        selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                        out.println("Неправильный выбор. Используется случайная сложность: " + selectedDifficulty);
                    } else {
                        selectedDifficulty = difficultyLevels.get(difficultyChoice - 1);
                    }
                } catch (NumberFormatException e) {
                    selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                    logger.error("Некорректный ввод. Выбран случайный уровень сложности: {}", selectedDifficulty, e);
                    out.println("Некорректный ввод. Используется случайная сложность: " + selectedDifficulty);
                }
            }

            String word = CategoryProvider.getRandomWord(selectedCategory, selectedDifficulty);
            Game game = new Game(word);

            out.println("Категория: " + selectedCategory);
            out.println("Сложность: " + selectedDifficulty);

            while (!game.isGameOver()) {
                out.println(game.getMaskedWord());
                out.println("Введите букву: ");
                String letterInput = scanner.nextLine().toLowerCase();

                if (letterInput.length() != 1 || !Character.isLetter(letterInput.charAt(0))) {
                    out.println("Ошибка ввода. Введите одну букву.");
                    continue;
                }

                char guess = letterInput.charAt(0);
                game.makeGuess(guess);

                if (game.isWordGuessed()) {
                    out.println("Поздравляем! Вы угадали слово: " + game.getWord());
                    break;
                } else if (game.isGameOver()) {
                    out.println("Вы проиграли. Загаданное слово было: " + game.getWord());
                }
            }
        } catch (Exception e) {
            logger.error("Произошла ошибка: ", e);
        } finally {
            scanner.close();
        }
    }
}
