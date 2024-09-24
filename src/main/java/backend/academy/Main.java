package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.Scanner;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(WordProvider.getRandomWord());

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
