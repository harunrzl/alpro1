import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Stacker  extends JFrame implements KeyListener {
	int iteration = 1;
	static double time = 200;
	static int last = 0;
	static int m = 10;
	static int n = 20;
	JButton b[][];
	static int length[] = {5,5};
	static int layer = 19;
	static int deltax[] = {0,0};
	static boolean press = false;
	static boolean forward = true;
	static boolean start = true;
	public static void main (String[] args){
		new Stacker();
	}
	
	public Stacker(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		b = new JButton [m][n];
        setLayout(new GridLayout(n,m));
        for (int y = 0;y<n;y++){
            for (int x = 0;x<m;x++){
                    b[x][y] = new JButton(" ");
                    b[x][y].setBackground(Color.white);
                    add(b[x][y]);
                    b[x][y].setEnabled(true);
            }//end inner for
    }
        setFocusable(true);
        addKeyListener(this);
        pack();
        setVisible(true);
        go();
	}
	
	public void go(){
		int tmp = 0;
		Component temporaryLostComponent = null;
		do{
		if (forward == true){
			forward();
		} else {
			back();
		}
		if (deltax[1] == 10-length[1]){
			forward = false;
		} else if (deltax[1] == 0){
			forward = true;
		}
		draw();
		try {
			Thread.sleep((long) time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}while(press == false);
		if (layer>12){
			time= 150-(iteration*iteration*2-iteration); 
		} else {
			time = time - 2.2;
		}
		iteration++;
		layer--;
		press = false;
		tmp = check();
		length[0] = length[1];
		length[1] = tmp;
		if (layer == -1){
			JOptionPane.showMessageDialog(temporaryLostComponent, "Congratulations! You beat the game!");
		}
		if (length[1] <= 0){	
			JOptionPane.showMessageDialog(temporaryLostComponent, "Game over! You reached line "+(18-layer)+"!");
            System.exit(0);
		}
		last = deltax[1];
		start = false;
		go();
	}
	public int check(){
		if (start == true){
			return length[1];
		} else if (last<deltax[1]){
			if (deltax[1]+length[1]-1 <= last+length[0]-1){
				return length[1];
			} else {
				return length[1]-Math.abs((deltax[1]+length[1])-(last+length[0]));
			}
		} else if (last>deltax[1]){
			return length[1]-Math.abs(deltax[1]-last);
		} else {
			return length[1];
		}
	}
	public void forward(){
		deltax[0] = deltax[1];
		deltax[1]++;
	}
	
	public void back(){
		deltax[0] = deltax[1];
		deltax[1]--;
	}
	
	public void draw(){
		for (int x = 0;x<length[1];x++){
			b[x+deltax[0]][layer].setBackground(Color.white);
			
			}
		for (int x = 0;x<length[1];x++){
			b[x+deltax[1]][layer].setBackground(Color.BLUE);
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			press = true;
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
