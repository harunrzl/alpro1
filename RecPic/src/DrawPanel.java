import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class DrawPanel extends JPanel {
	private boolean beginDraw;
	private boolean clear;
	private Color lineColor;
	
	public DrawPanel() {
		beginDraw=false;
		clear=false;
		lineColor=Color.BLACK;
		setBackground(Color.WHITE);
	}
	
	public Color getLineColor() { return(lineColor); }
	public void setLineColor(Color c) { lineColor=c; }
	
	public void setBeginDraw() { beginDraw=true; repaint(); }
	
	public void setClear() { clear=true; repaint(); }
	
	public void draw(Graphics g, int n, int x1, int y1, int x2, int y2){
		if(n==1) {
			g.setColor(lineColor);
			g.drawLine(x1, y1, x2, y2);
		} else {
			int x3=(x1+x2)/2;
			int y3=(y1+y2)/2;
			int x4=(x1+x3)/2;
			int y4=(y1+y3)/2;
			int y5=y4-(y4-y1);
			int x5=x4+(x4-x1)/2;
			//g.drawString(String.valueOf(a)+", "+String.valueOf(a2), x4, y4);
			draw(g,n-1,x1,y1,x5,y5);
			draw(g,n-1,x3,y3,x5,y5);
			draw(g,n-1,x2,y2,x5,y5);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(beginDraw) { draw(g,4,50,50,710,340);  beginDraw=false; }
		else if(clear) { 
			g.clearRect(0, 0, 760, 390); 
			clear=false; 
		}
	}

}
