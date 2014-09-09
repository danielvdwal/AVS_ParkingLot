package de.fh_koeln.avs.imageprocessor.view;

import de.fh_koeln.avs.global.ROI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;

/**
 *
 * @author Daniel van der Wal
 */
public class ROIPanel extends JPanel {

    private final ConcurrentHashMap<Integer, ROI> rois;
    private final int maxX, maxY;
    private int counter = 0;

    public ROIPanel(int maxX, int maxY) {
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        rois = new ConcurrentHashMap<>();
    }

    public void activateMouseListeners() {
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseMotionAdapter);
    }

    public void deactivateMouseListeners() {
        removeMouseListener(mouseAdapter);
        removeMouseMotionListener(mouseMotionAdapter);
    }

    public void setROIs(Map<Integer, ROI> rois) {
        this.rois.clear();
        this.rois.putAll(rois);
        counter = rois.size();
        repaint();
    }

    private ROI getROI(int id) {
        return rois.get(id);
    }

    public void addROI(int id, int x, int y) {
        ROI roi = new ROI(id, maxX, maxY);
        roi.setStartPoint(x, y);
        roi.setEndPoint(x, y);
        rois.put(id, roi);
    }

    public Map<Integer, ROI> getAllROIs() {
        return rois;
    }

    public void removeAllROIs() {
        rois.clear();
        counter = 0;
        repaint();
    }

    public void removeLastROI() {
        if (counter > 0) {
            counter--;
            rois.remove(counter);
            repaint();
        }
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

    private final MouseAdapter mouseAdapter = new MouseAdapter() {

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
    };

    private final MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) {
            ROI roi = getROI(counter);
            roi.setEndPoint(e.getX(), e.getY());
            repaint();
        }
    };
}
