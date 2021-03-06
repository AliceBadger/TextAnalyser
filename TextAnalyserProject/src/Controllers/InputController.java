package Controllers;
import java.io.File;
import java.util.Scanner;

public class InputController {
    public void initialize () {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            printMenu();
            try {
                FileController.downloadFile();
            }
            catch(Exception e) {
                System.out.println("Ups nie udało się pobrać pliku.");
            }
            int input = getUserInput();

            handleInput(input);
            if(input != 8) {
                System.out.println("Naciśnij dowolny przycisk aby kontynuować");
            }
            scanner.nextLine();
        }
    }

    private void printMenu() {
        System.out.println("1. Pobierz plik z internetu");
        System.out.println("2. Zlicz liczbę liter w pobranym pliku");
        System.out.println("3. Zlicz liczbę wyrazów w pliku");
        System.out.println("4. Zlicz liczbę znaków interpunkcyjnych w pliku");
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
        FileController fileController = new FileController();
        switch(input) {
            case 1:
                System.out.println("Pobieranie pliku");
                try {
                    FileController.downloadFile();
                }
                catch(Exception e) {
                    System.out.println("Ups, nie udało się pobrać pliku.");
                }
                break;
            case 2:
                fileController.printLettersInFileCount();
                break;
            case 3:
                int words = FileController.wordCount();
                System.out.printf("Plik ma %d wyrazów\n",words);
                break;
            case 4:
                fileController.printPunctationsMarksInFileCount();
                break;
            case 5:
                fileController.printStatementsInFileCount();
                break;
            case 6:
                String result = fileController.generateRaport(fileController.getFile("8.txt"));
                if(result == null) {
                    System.out.println("Brak pliku");
                } else {
                    System.out.println(result);
                }
                break;
            case 7:
                String data = fileController.generateRaport(fileController.getFile("8.txt"));
                if(data == null) {
                    System.out.println("Brak pliku źródłowego");
                } else {
                    boolean isSuccess = fileController.saveDataInFile(data);
                    if(isSuccess) {
                        System.out.println("Sukces!");
                    }
                }
                break;
            case 8:
                FileController.deleteFile("plik.txt");
                System.out.println("papa");
                System.exit(0);
                break;
        }
    }
}
