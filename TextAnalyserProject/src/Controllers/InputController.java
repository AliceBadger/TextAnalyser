package Controllers;
import java.util.Scanner;

public class InputController {
    // wyświetlanie menu, obsługiwanie inputu, zlecanie odpowiednich zadań na podstawie inputów
    public void initialize () {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            printMenu();
            int input = getUserInput();
            handleInput(input);
            System.out.println("Naciśnij dowolny przycisk aby kontynuować");
            scanner.nextLine();
        }

    }

    private void printMenu() {
        System.out.println("1. Pobierz plik z internetu");
        System.out.println("2. Zlicz liczbę liter w pobranym pliku");
        System.out.println("3. Zlicz liczbę wyrazów w pliku");
        System.out.println("4. Zlcz liczbę liczbę znaków interpunkcyjnych w pliku");
        System.out.println("5. Zlicz liczbę zdań w pliku");
        System.out.println("6. Wygeneruj raport o użyciu liter (A-Z)");
        System.out.println("7. Zapisz statystyki z punktów 2-5 do pliku statystyki.txt");
        System.out.println("8. Wyjście z programu");
    }

    private int getUserInput() {
        while (true) {
            System.out.println("Podaj opcję, którą chcesz wybrać");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(tryParseInt(input)) {
                int result = Integer.parseInt(input);
                if(result > 0 && result < 9) {
                    return result;
                }
            }
            System.out.println("Błędna wartość");
        }
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void handleInput (int input) {
        switch(input) {
            case 1:
                System.out.println("Pobranie pliku");
                break;
            case 2:
                System.out.println("ZLiczanieliter");
                break;
            case 3:
                System.out.println("Zliczanie wyrazów");
                break;
            case 4:
                System.out.println("Zliczanie znaków interounkcyjnych");
                break;
            case 5:
                System.out.println("Zliczanie zdań");
                break;
            case 6:
                System.out.println("Generowanie raportu");
                break;
            case 7:
                System.out.println("Zapisywanie w pliku");
                break;
            case 8:
                System.out.println("papa");
                break;
        }
    }
}
