import java.util.*; // <-- Scanner
class MyScanner {

	public static void main (String args[]) {

		Scanner sc = new Scanner(System.in);
		//sc.useRadix(4);

		System.out.print("Masukkan sebuah bilangan : ");
		double a = sc.nextDouble();
		String b = sc.next();
		double c = sc.nextDouble();

		double hasil; // Deklarasi
		hasil = 0; // Inisialisasi

		if(b.equals("+")) hasil=a+c;
		if(b.equals("-")) hasil=a-c;
		if(b.equalsIgnoreCase("x")) hasil=a*c;
		if(b.equals("/")) hasil=a/c;

		System.out.println("\nHasil nya adalah " +hasil);

/*		System.out.print("Masukkan bilangan kedua : ");
		long b = sc.nextLong();

		System.out.println("Hasil " +a+ " x " +b+ " adalah " +(a*b));
*/
	}
}
