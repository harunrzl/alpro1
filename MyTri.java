import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyTri extends JFrame {
	JPanel mypanel;
	JButton drawBut, clearBut, setLvlBut;
	JLabel mylabel;
	JTextArea textar;

	protected int height;
	protected int width;
	protected Color color;

	public MyTri() {
		setBounds(100, 100, 800, 500);
		mypanel = new JPanel();

		textar = new JTextArea(10, 40);
		mypanel.add(textar);

		drawBut = new JButton("Draw");
		drawBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mypanel.drawMe(g, new Point(100,140));
			}
		});
		drawBut.setBounds(12, 415, 97, 25);
		mypanel.add(drawBut);

		clearBut = new JButton("Clear");
		clearBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel.setClear();
			}
		});
		clearBut.setBounds(121, 415, 97, 25);
		mypanel.add(clearBut);

		setLvlBut = new JButton("Set Level");
		setLvlBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String theInput = JOptionPane.showInputDialog(null, "Set level to : ", "My Triangle", 1);
				if(theInput != null) {
//					JOptionPane.showMessageDialog(null
				} else {
					JOptionPane.showMessageDialog(null, "You just cancelled the action.", "My Triangle", 1);
				}
			}
		});

		mylabel = new JLabel();


		mypanel.add(setLvlBut);

//		panel = new DrawPanel();
//		panel.setBounds(12, 13, 758, 389);
//		contentPane.add(panel);

		mypanel.add(mylabel);
		this.add(mypanel);
	}

	public void drawMe(Graphics g, Point location) {
		g.setColor(color);
		Point point2 = new Point(location.x+width, location.y);
		Point point3 = new Point(location.x+(width/2),location.y - height);
		g.drawLine(location.x,location.y,point2.x,point2.y);
		g.drawLine(location.x,location.y,point3.x,point3.y);
		g.drawLine(point2.x,point2.y,point3.x,point3.y);
	}


    public void paints(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        int x1, y1, x2, y2, x3, y3;
        x1 = x2 = x3 = 200;
        y1 = y2 = y3 = 390;
        triangle(g, x1 = 174, y1, x2 = 204, y2, x3 = 189, y3 = 360);

        g.setColor(Color.green);
        g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        triangle(g, x1, y1, x2, y2, x3, y3);
    }

    void triangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }

	public static void main(String[] args) {
		MyTri first = new MyTri();
		first.setSize(500,250);
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first.setVisible(true);
	}

}
