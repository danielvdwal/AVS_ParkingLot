package de.fh_koeln.avs.dashboard;

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
    
    Collection<String> getConnectedImageCapturerNames();

    ImageData getRawImage(String clientName);

    Map<Integer, ROI> getROIs(String clientName);
}
