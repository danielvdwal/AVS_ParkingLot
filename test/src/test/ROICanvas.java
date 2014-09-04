package test;

import de.fh_koeln.avs.global.ROI;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Daniel van der Wal
 */
public class ROICanvas extends Canvas {

    private final ConcurrentHashMap<Integer, ROI> rois;
    private int counter = 0;
    private BufferedImage img;

    public ROICanvas() {
        rois = new ConcurrentHashMap<>();
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                addROI(counter, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ROI roi = getROI(counter);
                roi.calculateValues(e.getX(), e.getY());
                counter++;

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                ROI roi = getROI(counter);
                roi.calculateValues(e.getX(), e.getY());

                repaint();
            }
        });
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    private ROI getROI(int id) {
        return rois.get(id);
    }

    public void addROI(int id, int x, int y) {
        rois.put(id, new ROI(id, x, y));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        if (img != null) {
            g.drawImage(img, 0, 0, 640, 480, this);
        }
        rois.values().stream().forEach((roi) -> {
            g.setColor(Color.green);
            g.drawRect(roi.getX(), roi.getY(), roi.getWidth(), roi.getHeight());
            g.setColor(Color.black);
            g.drawString(roi.getId() + "", (int) (roi.getX() + (double) roi.getWidth() / 2), (int) (roi.getY() + (double) roi.getHeight() / 2));
        });
    }
}
