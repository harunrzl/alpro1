import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainMenu extends JFrame {
	private JFrame frame;
	private JPanel mainPanel;
	public static void main (String[] args) {
		new mainMenu();
	}

	public mainMenu() {
		frame = new JFrame("sempak");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		//mainPanel.setOpaque(true);

		JButton butnewgame = new JButton("Permainan baru");
		butnewgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		JPanel gamePanel = new JPanel();
		gamePanel.setOpaque(true);

				frame.remove(mainPanel);
				frame.add(gamePanel);
				frame.revalidate();
				frame.repaint();
				//NewGame(); // Memulai permainan
			}
		});
		mainPanel.add(butnewgame);

		frame.add(mainPanel);
		frame.setSize(300, 300);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
}
