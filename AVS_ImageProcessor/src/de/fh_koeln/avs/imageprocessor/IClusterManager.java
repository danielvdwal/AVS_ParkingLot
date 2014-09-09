package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManager {

    boolean connect();

    boolean disconnect();

    ImageData getRawImage();

    void sendROIs(Map<Integer, ROI> rois);
    
    void setId(String id);
    
    Collection<String> getConnectedImageCapturerNames();
}
