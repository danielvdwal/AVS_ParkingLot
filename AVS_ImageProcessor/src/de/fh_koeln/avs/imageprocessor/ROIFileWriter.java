package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ROI;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel van der Wal
 */
public class ROIFileWriter {
    
    private BufferedWriter writer;

    public synchronized void writeROIFile(String imageCapturerId, Map<Integer, ROI> rois) {
        try {
            writer = new BufferedWriter(new FileWriter(imageCapturerId + ".roi"));
            for (ROI roi : rois.values()) {
                writer.write(roi.getROIInformationAsString());
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ROIFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ROIFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
