package test;

import de.fh_koeln.avs.global.ROI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;

/**
 *
 * @author Daniel van der Wal
 */
public class ROIPanel extends JPanel {

    private final ConcurrentHashMap<Integer, ROI> rois;
    private int counter = 0;

    public ROIPanel() {
        rois = new ConcurrentHashMap<>();
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                addROI(counter, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ROI roi = getROI(counter);
                roi.setEndPoint(e.getX(), e.getY());
                counter++;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                ROI roi = getROI(counter);
                roi.setEndPoint(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private ROI getROI(int id) {
        return rois.get(id);
    }

    public void addROI(int id, int x, int y) {
        ROI roi = new ROI(id);
        roi.setStartPoint(x, y);
        rois.put(id, roi);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        rois.values().stream().forEach((roi) -> {
            g.setColor(Color.green);
            g.drawRect(roi.getX(), roi.getY(), roi.getWidth(), roi.getHeight());
            g.setColor(Color.black);
            g.drawString(roi.getId() + "", (int) (roi.getX() + (double) roi.getWidth() / 2), (int) (roi.getY() + (double) roi.getHeight() / 2));
        });
    }
}
