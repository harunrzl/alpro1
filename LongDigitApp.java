import java.util.*;
class LongDigitApp {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nMasukkan bilangan pertama :");
		LongDigit a = new LongDigit(sc.next());

		System.out.println("\nMasukkan bilangan kedua :");
		LongDigit b = new LongDigit(sc.next());

		System.out.println("\nHasil penjumlahannya adalah :");
		System.out.println(a.add(b));
	}
}
