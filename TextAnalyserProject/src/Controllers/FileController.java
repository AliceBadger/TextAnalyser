package Controllers;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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



}
