/*
 * Shapes.java
 *
 * Created on April 4, 2007, 11:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Shapes  extends JFrame {
    JTextField sizeInput = new JTextField(3);
    JButton triangleButton = new JButton("Draw Triangle");
    JButton squareButton = new JButton("Draw Square");
    JButton circleButton = new JButton("Draw Circle");
    ShapePanel shapePanel = new ShapePanel();
    
    /** Creates a new instance of Shapes */
    public Shapes() {
	JPanel sizePanel = new JPanel();
	sizePanel.add(new JLabel("Size"));
	sizePanel.add(sizeInput);
	
	JPanel buttonPanel = new JPanel();
	buttonPanel.add(triangleButton);
	buttonPanel.add(squareButton);
	buttonPanel.add(circleButton);
	
	JPanel topPanel = new JPanel();
	topPanel.setLayout(new GridLayout(2, 1));
	topPanel.add(sizePanel);
	topPanel.add(buttonPanel);
	
	
	//shapePanel.setSize(200, 200);
	shapePanel.setPreferredSize(new Dimension(200, 200));
	getContentPane().add(topPanel, BorderLayout.NORTH);
	getContentPane().add(shapePanel, BorderLayout.CENTER);
	pack();
	setVisible(true);
	
	ActionListener listener = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int size = Integer.parseInt(sizeInput.getText());
		Shape shape;
		if (e.getSource() == triangleButton)
		    shape = new Triangle();
		else if (e.getSource() == squareButton)
		    shape = new Square();
		else
		    shape = new Circle();
		
		shape.setSize(size);
		shapePanel.setShape(shape);
		repaint();
	    }
	};
	triangleButton.addActionListener(listener);
	squareButton.addActionListener(listener);
	circleButton.addActionListener(listener);
    }
    
    public static void main(String[] args) {
	new Shapes();
    }
}


class ShapePanel extends JPanel {
    Shape currentShape;
    public void setShape(Shape shape) {
	currentShape = shape;
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (currentShape != null)
		currentShape.draw(g);
    }
}



abstract class Shape {
    static final int DEFAULT_SIZE = 100;
    int size;
    public void setSize(int size) {
	this.size = size;
    }
    abstract public void draw(Graphics g);
}

class Triangle extends Shape {
    
    public void draw(Graphics g) {
	int sqrt3size = (int) (size*Math.sqrt(3));
	
	g.drawLine(size, size/2, 3*size/2, size/2+ sqrt3size/2);
	g.drawLine(3*size/2, size/2+ sqrt3size/2, size/2, size/2+ sqrt3size/2 );
	g.drawLine(size, size/2,  size/2, size/2+ sqrt3size/2 );
	
    }
}

class Square extends Shape {
    public void draw(Graphics g) {
	g.drawRect(size/2, size/2, size, size);
    }
    
}


class Circle extends Shape {
    public void draw(Graphics g) {
	g.drawOval(size/2, size/2, size, size);
    }
    
}
