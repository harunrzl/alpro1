import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyPointList extends JFrame {
	private JFrame frame;
	private JPanel panel;

	public static void main (String[] args) {
		new MyPointList();
	}

	public MyPointList() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Praktikum XVII");
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null); // Posisi window ke tengah

		panel = new JPanel();
		JTextArea textar = new JTextArea(10, 40);
		textar.setEnabled(false);
		panel.add(textar);

		JButton butGaris = new JButton("Garis");
		butGaris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//super.draw(g);
			}
		});
		panel.add(butGaris);

		frame.add(panel);
		frame.setVisible(true); // Menampilkan frame nya
	}

	private void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(30, 30, 200, 30);
		g2d.drawLine(200, 30, 30, 200);
		g2d.drawLine(30, 200, 200, 200);
		g2d.drawLine(200, 200, 30, 30);
	}
}
