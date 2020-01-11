package Controllers;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;


import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;


public class FileController {

    public File getFile(String fileName) {
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
                if (character == '.' || character == '!' || character == '?') {
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

    public static void downloadFile() throws MalformedURLException {
        try (BufferedInputStream in = new BufferedInputStream(new URL("https://s3.zylowski.net/public/input/8.txt").openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("8.txt")) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
        }
    }

    public static int wordCount() {
        String file = "8.txt";
        String words = null;
        try {
            words = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new NullPointerException("File not found");
        }
        String[] w = words.split("\\s");
        int wordsCount = 0;
        for(int i = 0; i < w.length; i++) {
            if( w[i].length() > 1) {
                wordsCount ++;
            }
        }
        return wordsCount;
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Plik nie istnieje");
        }
        else {
            file.delete();
        }
    }

    public void printPunctationsMarksInFileCount() {
        File file = getFile("8.txt");
        if (file != null) {
            int sum = getPunctationMarksCount(file);
            System.out.println("Liczba znaków interpunkcyjnych znajdujących się w pliku: " + sum);
        } else
            System.out.println("Brak pliku");
    }

    public boolean saveDataInFile (String data){
        try {
            File file = new File("statystyki.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("statystyki.txt"));
            bufferedWriter.write(data);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getPunctationMarksCount(File file) {
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                char character = 0;
                int sum = 0;
                while (fis.available() > 0) {
                    character = (char) fis.read();
                    int ascii = (int) character;
                    if (ascii == 46 || ascii == 44 || ascii == 33 || ascii == 63 || ascii == 58 || ascii == 59 ||
                            ascii == 45 || ascii == 96 || ascii == 34 || ascii == 39 || ascii == 40 || ascii == 41 ||
                            ascii == 91 || ascii == 93) {
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

    public String generateRaport(File file) {
        if(file != null) {
            int[] asciis = new int[255];
            try {
                FileInputStream fis = new FileInputStream(file);
                char character = 0;
                while (fis.available() > 0) {
                    character = (char) fis.read();
                    int ascii = (int) character;
                    asciis[ascii]++;
                }
                String data = "";
                for(int i = 65; i < 91; i++) {
                    data += Character.toString((char) i) + ": " + Integer.toString(asciis[i]) + "\n";
                }
                for(int i = 97; i < 123; i++) {
                    data += Character.toString((char) i) + ": " + Integer.toString(asciis[i]) + "\n";
                }
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
