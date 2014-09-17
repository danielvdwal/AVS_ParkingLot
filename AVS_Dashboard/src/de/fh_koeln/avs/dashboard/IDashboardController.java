package de.fh_koeln.avs.dashboard;

import de.fh_koeln.avs.global.ROI;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal
 */
public interface IDashboardController {
     
    boolean connectToCluster();

    boolean disconnectFromCluster();

    BufferedImage getRawImage(String clientName);
    
    Collection<String> getImageCapturerNames();
    
    Map<Integer, ROI> getROIs(String clientName);
}
