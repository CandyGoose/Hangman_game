package backend.academy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выбор категории
        List<String> categories = CategoryProvider.getCategories();
        System.out.println("Выберите категорию: ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        if (categoryChoice < 1 || categoryChoice > categories.size()) {
            System.out.println("Неправильный выбор, используется категория 'Животные'.");
            categoryChoice = 1;
        }

        String selectedCategory = categories.get(categoryChoice - 1);

        // Выбор уровня сложности
        List<String> difficultyLevels = CategoryProvider.getDifficultyLevels();
        System.out.println("Выберите уровень сложности: ");
        for (int i = 0; i < difficultyLevels.size(); i++) {
            System.out.println((i + 1) + ". " + difficultyLevels.get(i));
        }

        int difficultyChoice = scanner.nextInt();
        scanner.nextLine();

        if (difficultyChoice < 1 || difficultyChoice > difficultyLevels.size()) {
            System.out.println("Неправильный выбор, используется уровень сложности 'Средний'.");
            difficultyChoice = 2;
        }

        String selectedDifficulty = difficultyLevels.get(difficultyChoice - 1);

        String word = CategoryProvider.getRandomWord(selectedCategory, selectedDifficulty);

        Game game = new Game(word);
        System.out.println("Категория: " + selectedCategory);
        System.out.println("Уровень сложности: " + selectedDifficulty);

        // Игровой цикл
        while (!game.isGameOver()) {
            System.out.println(game.getMaskedWord());
            System.out.println("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Нужно ввести одну букву.");
                continue;
            }

            char guess = input.charAt(0);
            game.makeGuess(guess);

            if (game.isWordGuessed()) {
                System.out.println("Поздравляем, вы угадали слово: " + game.getWord());
                break;
            } else if (game.isGameOver()) {
                System.out.println("Вы проиграли! Загаданное слово было: " + game.getWord());
            }
        }

        scanner.close();
    }
}
