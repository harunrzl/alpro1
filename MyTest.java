import java.util.Scanner;
class MyTest {
	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukkan angka : ");
		Double a = sc.nextDouble();
		int x = (int) Math.floor((Double)a);
//		int length = a.length(); 
		//S1=(a1+b1+c1)%8;
//		int a1 = a.charAt(length-1);
		System.out.println(Integer.toOctalString(x));
	}
}
