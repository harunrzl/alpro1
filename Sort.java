import java.util.*;
public class Sort {

    public static void main(String[] args) throws IOException {
        String path;
        Scanner masuk = new Scanner(System.in);

        System.out.println("Masukkan alamat file :");
        path = masuk.nextLine();

        BufferedReader reader = null;
        PrintWriter writer = null;

        ArrayList<String> rows = new ArrayList<String>();

        
        try {
            
            //read file
            reader = new BufferedReader(new FileReader(path));
            String file;
            System.out.println("sebelum diurutkan:");
            while ((file = reader.readLine()) != null) {
                rows.add(file);
                System.out.println(file);
            }

            //Bubble Sort
            boolean finished = true;

            while (finished) {
                finished = false;
                for (int i = 0; i < rows.size() - 1; i++) {
                    String nam1 = rows.get(i);
                    String nam2 = rows.get(i + 1);
                    if (nam1.compareTo(nam2) > 0) {
                        String temp = rows.get(i);
                        rows.set(i, rows.get(i + 1));
                        rows.set(i + 1, temp);
                        finished = true;
                    }
                }
            }
                //write to file
                writer = new PrintWriter(new FileWriter(path));
                String[] strArr = rows.toArray(new String[0]);
                System.out.println("=============");
                System.out.println("Setelah diurutkan");
                for (String cur : strArr) {
                    writer.println(cur);
                    System.out.println(cur);
                }
            

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
