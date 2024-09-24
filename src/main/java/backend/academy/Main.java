package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Scanner;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> difficultyLevels = CategoryProvider.getDifficultyLevels();

        System.out.println("Выберите уровень сложности: ");
        for (int i = 0; i < difficultyLevels.size(); i++) {
            System.out.println((i + 1) + ". " + difficultyLevels.get(i));
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > difficultyLevels.size()) {
            System.out.println("Неправильный выбор, используется уровень сложности 'Средний'.");
            choice = 2;
        }

        String selectedDifficulty = difficultyLevels.get(choice - 1);
        String word = CategoryProvider.getRandomWord(selectedDifficulty);

        Game game = new Game(word);
        System.out.println("Уровень сложности: " + selectedDifficulty);

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
