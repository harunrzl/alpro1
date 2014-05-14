import java.io.*;
class MyFileReader {

	public static void main (String args[]) {

		try { 
			System.out.println(args[0]+ "\n");

			File f = new File(args[0]); // Bisa juga dalam bentuk --> new File("index.html");
			File f2 = new File(args[1]);

			// userDelimiter(); <-- mengubah limiter dalam scanner, default nya whitespace.

			FileReader fr = new FileReader(f);
			FileReader fr2 = new FileReader(f2);

			char a[] = new char[10];
			char b[] = new char[1000];

			while(fr.read(a,0,100)>-1) System.out.println(new String(a));

			System.out.println(args[1]+ "\n");
			while(fr2.read(b,0,100)>-1) System.out.println(new String(b));

		} catch (Exception e) { // Menangkap error yang ditemukan
			System.err.println("File "+args[0]+" tidak ditemukan!");
			//e.printStackTrace(); // Menampilkan error dalam system java
		};

	}

}
