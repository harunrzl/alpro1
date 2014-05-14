import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SierpinskisGasket {

    public static void main(String[] args) {
        new SierpinskisGasket();
    }

    public SierpinskisGasket() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            BaseShape base = new BaseShape(Math.min(getWidth(), getHeight()));
            Rectangle bounds = base.getBounds();
            int x = (getWidth() - bounds.width) / 2;
            int y = (getHeight() - bounds.height) / 2;
            base.transform(AffineTransform.getTranslateInstance(x, y));
            g2d.fill(base);
            g2d.dispose();
        }
    }

    public class BaseShape extends Path2D.Float {

        public BaseShape(float size) {

            float subSize = size / 2f;
            Triangle top = new Triangle(subSize);
            top.transform(AffineTransform.getTranslateInstance((size - subSize) / 2, 0));
            append(top, false);

            Triangle left = new Triangle(subSize);
            left.transform(AffineTransform.getTranslateInstance(0, subSize));
            append(left, false);

            Triangle right = new Triangle(subSize);
            right.transform(AffineTransform.getTranslateInstance(subSize, subSize));
            append(right, false);

        }

    }

    public class Triangle extends Path2D.Float {

        public Triangle(float size) {

            moveTo(size / 2f, 0);
            lineTo(size, size);
            lineTo(0, size);
            closePath();

        }

    }

}
