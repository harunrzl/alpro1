import java.util.Scanner ;
import java.lang.Math    ;

public class car {

	public static void main (String[] args) {
	
		Scanner ayoo=new Scanner(System.in) ;	

		montor a = new montor (); {
			a.awalan (ayoo)     ;
			//a.menuutama ()  ;
		}
	}
}


class montor {
	private static int tahunpembuatan,odometer,kecepatan,jarak,lamainjak,pilihan;
	private static String namapabrik;

	void awalan (ayoo) { 
		System.out.println("Tahun Pembuatan : ") ;
		tahunpembuatan=ayoo.nextInt();
		System.out.println("Nama Pabrik : ") ;
//		namapabrik=ayoo.next() ;
	}	 

/*	void menuutama () {
		System.out.println("1. Injak gas		") ;
		System.out.println("2. Injak rem		") ;
		System.out.println("3. Tambah jarak  	") ;
		System.out.println("4. Laporan			") ;
		System.out.println("5. Exit				") ;
		System.out.println()					   ;
		System.out.println("Masukkan pilihan 	") ;
		pilihan=ayoo.nextInt() 				   	   ;
		if(pilihan==1){injakgas       ();}menuutama ()  ; 
		if(pilihan==2){injakrem       ();}menuutama ()  ; 
		if(pilihan==3){jarak          ();}menuutama ()  ; 
		if(pilihan==4){infonya        ();}menuutama ()  ; 
		if(pilihan==5) {System.exit(0)  ;}
		else menuutama () ; 
	}	

	void waktu () {
		System.out.println("lamanya diijak : ")    ;
		lamainjak=ayoo.nextInt()					   ;
	}		

	void injakgas () {
		waktu () ; 
		kecepatan=kecepatan+3 ;
		jarak=waktu*kecepatan ;
		odometer+=jarak      ;
	}

	void injakrem () {
	    waktu () ;
		kecepatan-=3 ;
		if(kecepatan<0){System.out.println("mobil mundur") ;}
		if(kecepatan=0){System.out.println("mobil berhenti") ;}
		jarak=kecepatan*waktu ;
		odometer+=jarak ;
	}

	void jarak (){ 
		System.out.println("Masukkan jarak : ") ; 
		jarak=ayoo.nextInt();
		odometer+=jarak             ;
	}


	void info () {
		System.out.println("Tahun Pembuatan = " + tahunpembuatan) ;
		System.out.println("Nama Pabrik     = " + namapabrik)     ;
		System.out.println("Odometer        = " + jarak) ;
		System.out.println("Kecepatan       = " + kecepatan)      ;
		
	}
*/

}
