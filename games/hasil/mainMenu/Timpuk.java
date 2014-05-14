/**
 * Timpuk! Mini-Game
 *
 * Hasil dari Final Project mata kuliah Agoritma dan Pemrograman 1
 * Jurusan Sistem Informasi, Institut Teknologi Sepuluh Nopember.
 *
 * @version 1.0
 * @author Harun Rizal <harunr@cscpro.org>
 * @author Akbar Septriyan
 * @author Gilang Pandu Susandi
 * @author Haryo Bimo Wibisono
 * @author Ahmad Sirajuddin
 * @date January 2014
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Timpuk extends JFrame implements KeyListener {
	// Inisialisasi variabel
	private JFrame frame;
	private JPanel mainPanel, gamePanel;

	int langkah = 1; // Langkah pemain nya
	double speed = 200; // Kecepatan awal (dalam milisecond)
	int last = 0; // Posisi sebelumnya
	int baris = 20; // Rows (baris)
	int kolom = 10; // Cols (kolom)
	int layer = baris-1; // Posisi vertikal balok

	JButton b[][]; // Grid (kotak2)
	int panjang[] = {5,5}; // Panjang balok nya
	int posisi[] = {0,0}; // Posisi balok nya

	boolean pencet = false; // Tombol spasi belum ditekan
	boolean mentok = true; // Balok nya mentok di kiri saat baru dimulai
	boolean belum = true; // Loop belum dimulai

	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Timpuk();         
			}
		});
	}

	public Timpuk() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Timpuk! Mini-Game v1.0");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null); // Posisi window ke tengah
		frame.setLayout(new BorderLayout());

		Border outline = BorderFactory.createLineBorder(Color.black);
		JPanel tabsPanel = new JPanel();
		tabsPanel.setBorder(outline);

		JButton butnewgame = new JButton("New game");
		butnewgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.next(cardPanel);
			}
		});
		tabsPanel.add(butnewgame);
		frame.add(tabsPanel, BorderLayout.NORTH);

		mainMenu();
	}

	public void mainMenu() {
		mainPanel = new JPanel();
		JButton butnewgame = new JButton("Permainan baru");
		butnewgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(mainPanel);
				newgame(); // Memulai permainan
				frame.revalidate();
				frame.repaint();
				frame.add(gamePanel);
			}
		});
		mainPanel.add(butnewgame);

		frame.add(mainPanel);
		frame.setSize(300, 300);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public void newgame() {
		JPanel gamePanel = new JPanel();
		b = new JButton [kolom][baris]; // Inisialisasi grid
		setLayout(new GridLayout(baris, kolom)); // Membuat grid

		// Looping utk memuat grid baris & kolom
		for (int y=0; y<baris; y++) {
			for (int x=0; x<kolom; x++){
				b[x][y] = new JButton(" "); // Grid nya jadi terisi
				b[x][y].setBackground(Color.white); // As it said
				gamePanel.add(b[x][y]); // Masukkan grid nya ke frame
				b[x][y].setEnabled(false); // Button nya jadi tdk bisa di klik
			}
		}

		frame.setFocusable(true); // Set fokus nya ke main frame
		frame.addKeyListener(this); // Memasukkan fungsi keyboard
		frame.pack(); // Set ukuran window nya otomatis 
		frame.setVisible(true); // Menampilkan frame nya
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

		langkah++; // Langkah nya akan bertambah setiap mencet space
		layer--; // Layer yg dilalui akan berkurang
		pencet = false; // Reset tombol nya jadi false lagi

		// Menentukan apakah panjang blok nya akan berkurang
		tmp = ngecek(); // Cek disini
		panjang[0] = panjang[1];
		panjang[1] = tmp;

		if (panjang[1] <= 0){ // Jika panjang balok sudah habis
			JOptionPane.showMessageDialog(temporaryLostComponent, "Game over! Berakhir di level "+((baris-2)-layer)+"!");
			System.exit(0);
		}
		// Jika layer nya sudah habis (menang)
		if (layer == -1) JOptionPane.showMessageDialog(temporaryLostComponent, "Selamat! Anda dapat traktiran dari Akbar!");

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
		for (int x = 0; x<panjang[1]; x++) b[x+posisi[0]][layer].setBackground(Color.white);
		for (int x = 0; x<panjang[1]; x++) b[x+posisi[1]][layer].setBackground(Color.BLUE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
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
