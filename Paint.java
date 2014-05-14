import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paint{

 public static void main(String[] args){
    Win frame = new Win();
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	frame.add(new gui());
    frame.add(new gui());
//	frame.add(panel);
 }

}

class gui extends JComponent{
 Image image;
 Graphics2D draw;
 int x, y, prevX, prevY;

gui(){
        setDoubleBuffered(false);   
        addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                        prevX = e.getX();
                        prevY = e.getY();
                }

        });

        addMouseMotionListener(new MouseMotionAdapter(){
                public void mouseDragged(MouseEvent e){
                        x = e.getX();
                        y = e.getY();
                        draw.drawLine(prevX, prevY, x, y);
                        repaint();
                        prevX = x;
                        prevY = y;

                }


        });

}


public void changeColor(Color color)
{
    draw.setPaint(color);
    repaint();
}
public void clear(){
    draw.setPaint(Color.white);
    draw.fillRect(0, 0, getSize().width, getSize().height);
    draw.setPaint(Color.black);
    repaint();
}
public void paintComponent(Graphics g)
{
    if(image == null)
    {
        image = createImage(getSize().width, getSize().height);
        draw = (Graphics2D)image.getGraphics();
        draw.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        clear();
    }

    g.drawImage(image, 0, 0, null);
 }
} 





class Win extends JFrame implements ActionListener{
    JButton red, green, blue, clear;
    gui gui = new gui();

    Win(){
        super("Paint");
        setSize(500,500);


        Container content=new Container();
        content.setLayout(new BorderLayout());
        content.add(gui, BorderLayout.CENTER);
        content.setVisible(true);

        JPanel panel = new JPanel();
        content.add(panel, BorderLayout.SOUTH);

        panel.setPreferredSize(new Dimension(32, 68));
        panel.setMinimumSize(new Dimension(32, 68));
        panel.setMaximumSize(new Dimension(32, 68));


        red = new JButton("Red");
        green = new JButton("Green");
        blue = new JButton("Blue");
        clear = new JButton("Clear");

        red.setPreferredSize(new Dimension(50, 16));
        green.setPreferredSize(new Dimension(50,16));
        blue.setPreferredSize(new Dimension(50, 16));

        panel.add(red);
        panel.add(green);
        panel.add(blue);
        panel.add(clear);

        panel.setVisible(true);

        red.addActionListener(this);
        green.addActionListener(this);
        blue.addActionListener(this);
        clear.addActionListener(this);




    }
    public void actionPerformed(ActionEvent e) {

        if( e.getSource()==red){
            gui.changeColor(Color.red);
            repaint();
        }
        if( e.getSource()==green){
            gui.changeColor(Color.green);
            repaint();
        }
        if( e.getSource()==blue){
            gui.changeColor(Color.blue);
            repaint();
        }
        if( e.getSource()==clear){
            gui.clear();
        }


    }
}

