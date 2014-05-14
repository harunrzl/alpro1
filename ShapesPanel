public class ShapesPanel extends JPanel {

    private java.util.List shapesList ;

    /**
     * Constructs <code>ShapesPanel</code>
     */
    public ShapesPanel() {
        shapesList = new java.util.ArrayList() ;
        this.addMouseListener(new MouseClickListener())  ;
    }


    /**
     * Creates a shape
     * @param bounds
     * @return
     */
    private Shape createShape( Rectangle bounds ) {
        Shape shape = new Ellipse2D.Double(bounds.x, bounds.y, bounds.width, bounds.height );

/*
        To use the following shapes, you need to have java shapes library, which can
        be downloaded from <a href="http://wwww.jshapes.com">Java Shapes Library</a>
*/
/*
        // To create star shape
        Shape shape = new Star( bounds, 50, Star.STAR_8_POINTS );
        // To create triangle shape
        Shape shape = new Triangle( bounds, Triangle.UP );
        // To create diamond shape
        Shape shape = new Diamond( bounds );
        // To create Parallelogram shape
        Shape shape = new Parallelogram( bounds  );
*/

        return shape ;
    }

    /**
     *  MouseClickListener
     */
    private class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point pt = e.getPoint();
            Dimension size = new Dimension(100, 100 );
            Rectangle bounds = new Rectangle(pt.x, pt.y, size.width, size.height );
            Shape shape = createShape(bounds);
            shapesList.add( shape );
            repaint();
        }
    }

    /**
     * Paints the component
     * @param g
     */
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds = new Rectangle(0,0,getWidth(), getHeight() );
        g2.setPaint( Color.white );
        g2.fill( bounds );

        for (Iterator iterator = shapesList.iterator(); iterator.hasNext(); ) {
            Shape shape = (Shape) iterator.next();
            g2.setPaint( Color.cyan );
            g2.fill( shape );
            g2.setPaint( Color.black );
            g2.draw( shape );
        }
    }

    /**
     * Driver
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Shapes") ;
        frame.getContentPane().add( new ShapesPanel() );
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setVisible( true );

    }
}
