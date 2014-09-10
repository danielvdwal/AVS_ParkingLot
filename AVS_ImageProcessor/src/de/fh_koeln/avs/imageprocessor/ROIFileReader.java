package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ROI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel van der Wal
 */
public class ROIFileReader {

    private final Map<Integer, ROI> rois = new ConcurrentHashMap<>();
    private BufferedReader reader;

    public boolean roiFileExists(String imageCapturerId) {
        return new File(imageCapturerId + ".roi").exists();
    }

    public synchronized Map<Integer, ROI> readROIFile(String imageCapturerId) {
        rois.clear();
        try {
            reader = new BufferedReader(new FileReader(imageCapturerId + ".roi"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] roiParts = line.split(";");
                int id = Integer.parseInt(roiParts[0]);
                int x1 = Integer.parseInt(roiParts[1]);
                int y1 = Integer.parseInt(roiParts[2]);
                int x2 = Integer.parseInt(roiParts[3]);
                int y2 = Integer.parseInt(roiParts[4]);
                int maxX = Integer.parseInt(roiParts[5]);
                int maxY = Integer.parseInt(roiParts[6]);
                ROI roi = new ROI(id, maxX, maxY);
                roi.setStartPoint(x1, y1);
                roi.setEndPoint(x2, y2);
                rois.put(id, roi);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ROIFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ROIFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rois;
    }
}
