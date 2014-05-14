import javax.swing.JPanel;
import java.awt.*;

public class TrianglePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int level;
	private Color color;
	private boolean draw, clear;
	
	public TrianglePanel(){
		level=0; draw=false; clear=false; color = Color.RED;
	}
	
	public int getLevel() {return(level);}
	
	public void setLevel (int i) {level = i;}
	
	public void setDrawing(boolean b){draw=b; repaint();}
	
	public void setClearing(boolean b) {clear=b; repaint();}
	
	public void paint(Graphics g){
		super.paint(g);
		if(draw) {
			triangle(g,level,307,10,10,480,605,480); //coordinates of triangle//
			draw = false;
		} else if(clear){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);
			clear = false;
			repaint();
		}
	}
	private void triangle(Graphics g, int n, int x1, int y1, int x2, int y2, int x3, int y3) {
		//draw the triangle//
		if (n==0){
			g.setColor(color);
			g.drawLine(x1,y1,x2,y2);
			g.drawLine(x1,y1,x3,y3);
			g.drawLine(x3,y3,x2,y2);
		//draw the fractal recursively//
		} else {
			int x6=(x2+x3)/2;
			int y6=y2;
			int x4=(x2+x6)/2;
			int y4=(y1+y6)/2;
			int x5=(x6+x3)/2;
			int y5=y4;
			triangle(g,n-1,x1,y1,x4,y4,x5,y5);
			//triangle(g,n-1,x6,y6,x4,y4,x5,y5);
			triangle(g,n-1,x4,y4,x2,y2,x6,y6);
			triangle(g,n-1,x5,y5,x6,y6,x3,y3);
		}
	}
}
