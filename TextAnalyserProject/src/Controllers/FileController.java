package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;


public class FileController {

    private File getFile(String fileName) {
        File file = new File(fileName);
        boolean fileExists = file.exists();
        if (fileExists) {
            return file;
        } else {
            return null;
        }
    }

    private int getLettersCount(File file) {
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                char character = 0;
                int sum = 0;
                while (fis.available() > 0) {
                    character = (char) fis.read();
                    int ascii = (int) character;
                    if ((ascii >= 64 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                        sum++;
                    }
                }
                return sum;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void printLettersInFileCount() {
        File file = getFile("8.txt");
        if (file != null) {
            int sum = getLettersCount(file);
            System.out.println("Liczba liter znajdujących się w pliku: " + sum);
        } else
            System.out.println("Brak pliku");
    }

    private int getStatementsCount(File file) {
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                char character = 0;
                int sum = 0;
                char prevCharacter = 0;
                while (fis.available() > 0) {
                    character = (char) fis.read();
                    if (character == ' ') {
                        if (prevCharacter == '.' || prevCharacter == '!' || prevCharacter == '?') {
                            sum++;
                        }
                    }
                    prevCharacter = character;
                }
                if(character == '.'|| character == '!' || character == '?') {
                    sum++;
                }
                return sum;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;

    }

    public void printStatementsInFileCount() {
        File file = getFile("8.txt");
        if (file != null) {
            int sum = getStatementsCount(file);
            System.out.println("Liczba zdań pliku: " + sum);
        } else
            System.out.println("Brak pliku");
    }


}
