package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ROI;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageProcessorController {

    boolean connectToCluster();

    boolean disconnectFromCluster();

    void pullRawImage();

    BufferedImage getImage();

    void drawROIsAutomatically(int threshold, int minLineSize, int lineGap, boolean horizontal);

    Map<Integer, ROI> getROIs();
    
    void setROIs(Map<Integer, ROI> rois);

    void processImage();

    String getProcessedImageChunksInformation();

    void setSelectedImageCapturerName(String name);

    Collection<String> getImageCapturerNames();
}
