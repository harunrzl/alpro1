import java.io.*;
import java.util.*;

public class Sorting {
    public static void main(String[] args) {
        String path;
        Scanner masuk = new Scanner(System.in);

        System.out.println("Masukkan alamat file :");
        path = masuk.nextLine();
        File file = new File(path);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
