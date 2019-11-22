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

public class FileController {

    public static void downloadFile() throws MalformedURLException {


        try (BufferedInputStream in = new BufferedInputStream(new URL("https://s3.zylowski.net/public/input/8.txt").openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("plik.txt")) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            // handle exception
        }

    }

    public static int punctuationMarkCount() {
        String file = "plik.txt";
        String words = null;
        try {
            words = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new NullPointerException("File not found");
        }
        int number = 0;
        char array[] = {',','.', ';', ':','-', '?', '!', '"', '(', ')'};
        for (int i = 0, length = words.length(); i < length; i++) {
            if (new String(array).contains(String.valueOf(words.charAt(i)))) {

                number++;
            }

        }
        return number;

    }
    public static int charCount() {
        String file = "plik.txt";
        String words = null;
        try {
            words = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new NullPointerException("File not found");
        }
        int number = 0;
        for (int i = 0, length = words.length(); i < length; i++) {
            if (words.charAt(i) != ' ') {
                number++;
            }
        }
        return number;

    }
    public static int wordCount()

      {
            String file = "plik.txt";
            String words = null;
            try {
                words = new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException e) {
                throw new NullPointerException("File not found");
            }
            String[] w = words.split("\\s");
            return w.length - 1;
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



}
