package de.fh_koeln.avs.dashboard.view;

import de.fh_koeln.avs.global.ROI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;

/**
 *
 * @author Daniel van der Wal
 */
public class ROIPanel extends JPanel {

    private final ConcurrentHashMap<Integer, ROI> rois;

    public ROIPanel() {
        super();
        rois = new ConcurrentHashMap<>();
    }

    public void setROIs(Map<Integer, ROI> rois) {
        this.rois.clear();
        if (!rois.isEmpty()) {
            this.rois.putAll(rois);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        rois.values().stream().forEach((roi) -> {
            if (roi.isObjectDetected()) {
                g.setColor(new Color(255, 0, 0, 92));
            } else {
                g.setColor(new Color(0, 255, 0, 92));
            }
            g.fillRect(roi.getX(), roi.getY(), roi.getWidth(), roi.getHeight());
            g.setColor(Color.black);
            g.drawString(roi.getId() + "", (int) (roi.getX() + (double) roi.getWidth() / 2), (int) (roi.getY() + (double) roi.getHeight() / 2));
        });
    }
    
}
