package test;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author dvanderw
 */
public class TestFrame extends JFrame {

    private final JLabel image;
    private final ROIPanel panel;

    public TestFrame() {
        super();
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File("parkinglot_0.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        panel = new ROIPanel();
        panel.setOpaque(false);
        panel.setSize(new Dimension(640, 480));
        add(panel);
        
        image = new JLabel();
        image.setSize(new Dimension(640, 480));
        image.setIcon(getScaledImage(temp, 640, 480));
        add(image);

        //pack();
        setSize(660, 520);
        setVisible(true);
    }

    private ImageIcon getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        new TestFrame();
    }
}
