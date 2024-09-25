package backend.academy;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        try {
            List<String> categories = CategoryProvider.getCategories();
            System.out.println("Выберите категорию (или нажмите Enter для случайного выбора): ");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }

            String selectedCategory;
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                selectedCategory = categories.get(random.nextInt(categories.size()));
                System.out.println("Случайная категория: " + selectedCategory);
            } else {
                try {
                    int categoryChoice = Integer.parseInt(input);
                    if (categoryChoice < 1 || categoryChoice > categories.size()) {
                        selectedCategory = categories.get(random.nextInt(categories.size()));
                        System.out.println("Неправильный выбор. Используется случайная категория: " + selectedCategory);
                    } else {
                        selectedCategory = categories.get(categoryChoice - 1);
                    }
                } catch (NumberFormatException e) {
                    selectedCategory = categories.get(random.nextInt(categories.size()));
                    System.out.println("Некорректный ввод. Используется случайная категория: " + selectedCategory);
                }
            }

            // Выбор уровня сложности
            List<String> difficultyLevels = CategoryProvider.getDifficultyLevels();
            System.out.println("Выберите уровень сложности (или нажмите Enter для случайного выбора): ");
            for (int i = 0; i < difficultyLevels.size(); i++) {
                System.out.println((i + 1) + ". " + difficultyLevels.get(i));
            }

            String selectedDifficulty;
            input = scanner.nextLine();

            if (input.isEmpty()) {
                selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                System.out.println("Случайная сложность: " + selectedDifficulty);
            } else {
                try {
                    int difficultyChoice = Integer.parseInt(input);
                    if (difficultyChoice < 1 || difficultyChoice > difficultyLevels.size()) {
                        selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                        System.out.println("Неправильный выбор. Используется случайный уровень сложности: " + selectedDifficulty);
                    } else {
                        selectedDifficulty = difficultyLevels.get(difficultyChoice - 1);
                    }
                } catch (NumberFormatException e) {
                    selectedDifficulty = difficultyLevels.get(random.nextInt(difficultyLevels.size()));
                    System.out.println("Некорректный ввод. Используется случайный уровень сложности: " + selectedDifficulty);
                }
            }

            String word = CategoryProvider.getRandomWord(selectedCategory, selectedDifficulty);

            Game game = new Game(word);
            System.out.println("Категория: " + selectedCategory);
            System.out.println("Уровень сложности: " + selectedDifficulty);

            // Игровой цикл
            while (!game.isGameOver()) {
                System.out.println(game.getMaskedWord());
                System.out.println("Введите букву: ");
                String letterInput = scanner.nextLine().toLowerCase();

                if (letterInput.length() != 1 || !Character.isLetter(letterInput.charAt(0))) {
                    System.out.println("Ошибка ввода. Введите одну букву.");
                    continue;
                }

                char guess = letterInput.charAt(0);
                game.makeGuess(guess);

                if (game.isWordGuessed()) {
                    System.out.println("Поздравляем! Вы угадали слово: " + game.getWord());
                    break;
                } else if (game.isGameOver()) {
                    System.out.println("Вы проиграли. Загаданное слово было: " + game.getWord());
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
