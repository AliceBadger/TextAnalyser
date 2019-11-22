package Controllers;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileController {

    public static void downloadFile() throws MalformedURLException {

        URL website = new URL("https://s3.zylowski.net/public/input/8.txt");
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


}
