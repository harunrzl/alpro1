/**
 * Timpuk! Mini-Game
 *
 * Hasil dari Final Project mata kuliah Agoritma dan Pemrograman 1
 * Jurusan Sistem Informasi, Institut Teknologi Sepuluh Nopember.
 *
 * Cara memainkan
 * * Anda hanya butuh untuk menekan enter untuk menyamakan balok
 * * dengan balok dibawahnya hingga mencapai puncak diatas.
 *
 * @version 1.0
 * @author Harun Rizal <harunr@cscpro.org>
 * @author Akbar Septriyan <septriyan.akbar2@gmail.com>
 * @author Gilang Pandu Susandi <gilang.strife@gmail.com>
 * @author Haryo Bimo Wibisono <h.bimo.w@gmail.com>
 * @author Ahmad Sirajuddin <jundy.com@gmail.com>
 * @date January 2014
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Timpuk extends JFrame implements KeyListener {
	// Inisialisasi variabel
	int langkah = 1; // Langkah pemain nya
	double speed = 100; // Kecepatan awal (dalam milisecond)
	int last = 0; // Posisi sebelumnya
	int baris = 20; // Rows (baris)
	int kolom = 10; // Cols (kolom)
	int layer = baris-1; // Posisi vertikal balok
	String GameOverText;

	JButton b[][]; // Grid (kotak2)
	int panjang[] = {5,5}; // Panjang balok nya
	int posisi[] = {0,0}; // Posisi balok nya

	boolean pencet = false; // Tombol enter belum ditekan
	boolean mentok = true; // Balok nya mentok di kiri saat baru dimulai
	boolean belum = true; // Loop belum dimulai

	public static void main (String[] args) {
		new Timpuk();
	}

	public Timpuk() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Tombol exit
		b = new JButton [kolom][baris]; // Inisialisasi grid
		setLayout(new GridLayout(baris, kolom)); // Membuat grid

		// Looping utk memuat grid baris & kolom
		for (int y=0; y<baris; y++) {
			for (int x=0; x<kolom; x++){
				b[x][y] = new JButton(" "); // Grid nya jadi terisi
				b[x][y].setBackground(Color.BLACK); // As it said
				add(b[x][y]); // Masukkan grid nya ke frame
				b[x][y].setEnabled(false); // Button nya jadi tdk bisa di klik
			}
		}

		setLocationRelativeTo(null); // Posisi window ke tengah
		setTitle("Timpuk! Mini-Game v1.0"); // Judul nya bro heheh
		setFocusable(true); // Set fokus nya ke main frame
		addKeyListener(this); // Memasukkan fungsi keyboard

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (getWidth() / 2), middle.y - (getHeight() / 2));
		setLocation(newLocation);
		pack(); // Set ukuran window nya otomatis 
		setVisible(true); // Menampilkan frame nya
		JOptionPane.showMessageDialog(this, "Cara memainkan: tekan enter untuk menyamakan balok nya!");
		go(); // Memulai fungsi go()
	}

	public void go() {
		int tmp = 0;
		Component temporaryLostComponent = null;
		do { // Muter-muterin balok nya
			if (mentok == true) kekanan(); // Goyang ke kanan
			else kekiri(); // Goyang ke kiri boo

			if (posisi[1] == 10-panjang[1]) mentok = false; // Jika sdh mentok di kiri maka <-
			else if (posisi[1] == 0) mentok = true; // Jika sdh mentok di kanan lagi maka <-
			draw(); // Tampilkan GUI nya
			try {
				Thread.sleep((long) speed); // Kecepatan putaran nya
			} catch (InterruptedException e) {
				e.printStackTrace(); // Buang error message nya
			}
		} while(pencet == false); // ^jika tombolnya ga dipencet

		if(layer>baris-7) speed = 150-(langkah*langkah*2-langkah); // Kecepatannya bertambah sekian ms
		else speed = speed - 2.2; // Perbedaan kecepatan yg bertambah setelah lvl 7++

		langkah++; // Langkah nya akan bertambah setiap mencet enter
		layer--; // Layer yg dilalui akan berkurang
		pencet = false; // Reset tombol nya jadi false lagi

		// Menentukan apakah panjang blok nya akan berkurang
		tmp = ngecek(); // Cek disini
		panjang[0] = panjang[1];
		panjang[1] = tmp;

		// // Jika panjang balok sudah habis
		if (panjang[1] <= 0) {
			int levelPiro = ((baris-2)-layer);
			if(levelPiro<=3) GameOverText = "Anak TK aja bisa lebih tinggi dari itu :p";
			else if(levelPiro<=5) GameOverText = "Payah! Baru level segini udah kalah!";
			else if(levelPiro<=7) GameOverText = "Cemungud yahhh kk!";
			else if(levelPiro<=9) GameOverText = "Maju beroooooooooo!";
			else if(levelPiro<=11) GameOverText = "Lagi! Lagi! Lagi!";
			else if(levelPiro<=13) GameOverText = "Nanggung beroo, ayo lanjutin lagi!";
			else if(levelPiro<=15) GameOverText = "Ayo dikit lagi! Ada Asmirandah lagi nunggu kamu!";
			else if(levelPiro<=15) GameOverText = "Terossssss sampek tombol enter keyboard mu rusak!";
			else if(levelPiro<=19) GameOverText = "wakwakwakwk nanggung WAKWAKWKAWK";

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this,
						GameOverText+ " (level " +levelPiro+ "). Mau coba lagi?",
						"Game Over!", dialogButton
			);
			if(dialogResult==0) { this.dispose(); new Timpuk(); }
			else System.exit(0);
		}

		// Jika layer nya sudah habis (menang)
		if (layer == -1) {
			JOptionPane.showMessageDialog(temporaryLostComponent, "Selamat! Anda dapat traktiran dari Akbar!");
			System.exit(0);
		}

		last = posisi[1]; // Set posisi sebelumnya
		belum = false; // Loop pertama selesai
		go(); // Looping, balik ke atas lagi
	}

	public int ngecek() {
		if(belum == true) return panjang[1]; // Jika ini loop pertama (baru dimulai)
		else if(last<posisi[1]) { // Jika posisi sebelumnya gak sama dg posisi skrg
			if(posisi[1]+panjang[1]-1 <= last+panjang[0]-1) return panjang[1];
			else return panjang[1]-Math.abs((posisi[1]+panjang[1])-(last+panjang[0]));
		} else if(last>posisi[1]) return panjang[1]-Math.abs(posisi[1]-last);
		else return panjang[1];
	}

	public void kekanan() {
		posisi[0] = posisi[1];
		posisi[1]++;
	}
	
	public void kekiri() {
		posisi[0] = posisi[1];
		posisi[1]--;
	}
	
	public void draw() {
		for (int x = 0; x<panjang[1]; x++) b[x+posisi[0]][layer].setBackground(Color.BLACK);
		for (int x = 0; x<panjang[1]; x++) b[x+posisi[1]][layer].setBackground(Color.GREEN);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			pencet = true;
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
