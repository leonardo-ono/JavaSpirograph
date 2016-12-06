package javaspirograph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author leonardo
 */
public class Spirograph extends JPanel {

    private int r1 = 210;
    private double a1 = Math.toRadians(0);
    private int r2 = 110;
    private double a2 = (a1 * r1) / r2;
    
    private BufferedImage paper;
    private Stroke stroke = new BasicStroke(2);
    
    public Spirograph(int width, int height) {
        setOpaque(true);
        paper = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        paper.getGraphics().fillRect(0, 0, width, height);
    }
        
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.drawImage(paper, 0, 0, null);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(1, -1);
        
        g2d.setStroke(stroke);
        
        g2d.drawOval(-r1, -r1, 2 * r1, 2 * r1);
        g2d.rotate(a1);
        
        a2 = (a1 * r1) / r2;
        
        Point hole = new Point(80, 10);
        
        g2d.translate(r1 - r2, 0);
        g2d.rotate(-a2);
        g2d.setColor(Color.RED);
        g2d.drawOval(-r2, -r2, 2 * r2, 2 * r2);
        g2d.drawLine(0, 0, r2, 0);
        g2d.fillOval(hole.x - 5, hole.y - 5, 10, 10);
        
        Graphics2D goff = (Graphics2D) paper.getGraphics();
        goff.transform(g2d.getTransform());
        goff.setColor(Color.BLUE);
        goff.fillOval(hole.x - 1, hole.y - 1, 2, 2);
        
        a1 += 0.02;
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Spirograph test");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(new Spirograph(800, 600));
                frame.setVisible(true);
            }
        });
    }
    
}
